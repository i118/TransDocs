Ext.define("TransDocs.controller.document.OrderDocumentController", {
    extend: "TransDocs.controller.document.AbstractDocumentController",
    alias: "controller.orderDocumentController",

    requires: [
        "TransDocs.model.document.OrderDocumentModel",
        "TransDocs.service.ContractorService",
        "TransDocs.service.DictionaryService",
        "TransDocs.view.Spinner"
    ],

    initViewModel: function(vm){
        var spinner = Ext.create("TransDocs.view.Spinner");
        spinner.setViewModel(vm);
        spinner.addBind("{document}");
        spinner.addBind("{customer}");
        spinner.addBind("{document.customerPerson}");
        spinner.addBind("{carrier}");
        spinner.addBind("{document.carrierPerson}");
        spinner.addBind("{customerPersons}");
        spinner.addBind("{carrierPersons}");
        vm.getView().spinner = spinner;
    },

    changeCustomer: function(combo, newValue, oldValue, eOpts){
        var mainPanel = this.lookupReference("orderMainPanel");
        var viewModel = mainPanel.lookupViewModel();
        var document = viewModel.get("document");
        if (!newValue) {
            viewModel.set("customer", null);
        }
    },

    changeCarrier: function(combo, newValue, oldValue, eOpts){
        var mainPanel = this.lookupReference("orderMainPanel");
        var viewModel = mainPanel.lookupViewModel();
        var document = viewModel.get("document");
        if (!newValue) {
            viewModel.set("carrier", null);
        }
    },

    changeManager: function(combo, newValue, oldValue, eOpts){
        var mainPanel = this.lookupReference("orderMainPanel");
        var viewModel = mainPanel.lookupViewModel();
        var document = viewModel.get("document");
        if (!newValue) {
            document.setManager(null);
        }
    },

    findManager: function (combox, trigger, event) {
        var mainPanel = this.lookupReference("orderMainPanel");
        var orderWindow = mainPanel.up('window');
        var session = mainPanel.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("user_dictionary", orderWindow,  session, this, combox);
    },

    findCustomer: function (combox, trigger, event) {
        var mainPanel = this.lookupReference("orderMainPanel");
        var orderWindow = mainPanel.up('window');
        var session = mainPanel.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("customer_dictionary", orderWindow,  session, this, combox);
    },

    findCarrier: function (combox, trigger, event) {
        var mainPanel = this.lookupReference("orderMainPanel");
        var orderWindow = mainPanel.up('window');
        var session = mainPanel.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("carrier_dictionary", orderWindow,  session, this, combox);
    },

    findOrderTitle: function(combox, trigger, event){
        var mainPanel = this.lookupReference("orderMainPanel");
        var orderWindow = mainPanel.up('window');
        var session = mainPanel.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("order_title", orderWindow,  session, this, combox);
    },

    findPaymentDate: function(combox, trigger, event){
        var mainPanel = this.lookupReference("orderMainPanel");
        var orderWindow = mainPanel.up('window');
        var session = mainPanel.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("payment_date", orderWindow,  session, this, combox);
    },

    selectCustomerPerson: function (combo, record, eOpts) {
        var mainPanel = this.lookupReference("orderMainPanel");
        var viewModel = mainPanel.lookupViewModel();
        var document = viewModel.get("document");
        var newValue = record;
        if (document.getCustomerPerson() && document.getCustomerPerson().getId() == newValue.getId())return;
        document.setCustomerPerson(newValue);
    },

    selectCarrierPerson: function (combo, record, eOpts) {
        var mainPanel = this.lookupReference("orderMainPanel");
        var viewModel = mainPanel.lookupViewModel();
        var document = viewModel.get("document");
        var newValue = record;
        if (document.getCarrierPerson() && document.getCarrierPerson().getId() == newValue.getId())return;
        document.setCarrierPerson(newValue);
    },

    viewPerson: function (combox, trigger, event) {
        var selection = combox.getSelection();
        if (selection) {
            var mainPanel = this.lookupReference("orderMainPanel");
            var session = mainPanel.lookupSession().spawn();
            var orderWindow = mainPanel.up('window');
            var viewModel = orderWindow.lookupViewModel();
            var document = viewModel.get("document");
            TransDocs.service.ContractorService.openPerson(selection, orderWindow, session, false);
        }
    },

    saveOrderDocument: function () {
        var view = this.getView();
        view.setLoading(true, true);
        var viewModel = view.lookupViewModel();
        var document = viewModel.get("document");
        if (!document.isDirty()) {
            view.setLoading(false);
            return;
        }
        var me = this;
        document.save({
            success: function () {
                me.reloadCallerComponent();
                document.refresh(orderStore, function(records, operation, success){
                    if(success) {
                        document = records[0];
                        view.setLoading(false);
                        //Если не нулить то какого то х... не обновляются контролы
                        viewModel.set("document", null);
                        viewModel.set("document", document);
                        view.setTitle("Заявка № " + document.getOutgoingNumber().get("number")+" от "+document.getOutgoingNumber().getFormatDate());
                    }
                })
            }
        });
    },

    saveAndCloseOrder: function () {
        var view = this.getView();
        view.setLoading(true, true);
        var viewModel = view.lookupViewModel();
        var document = viewModel.get("document");
        var orderStore = viewModel.getStore("orderStore");
        var me = this;
        document.save({
            success: function () {
                view.setLoading(false);
                me.reloadCallerComponent();
                me.closeOrder();
            }
        });
    },

    reloadCallerComponent: function(){
        var view = this.getView();
        if(view.callerComponent) {
            var journalStore = view.callerComponent.lookupViewModel().getStore("orderStore");
            journalStore.reload();
        }
    },

    closeOrder: function () {
        var view = this.getView();
        view.close();
    }
});