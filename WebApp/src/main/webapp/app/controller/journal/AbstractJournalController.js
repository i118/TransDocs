Ext.define("TransDocs.controller.journal.AbstractJournalController", {
    extend: "Ext.app.ViewController",

    requires: [
        "TransDocs.service.SpecificationService"
    ],

    specification: function (store, operation) {
        var viewModel = this.lookupViewModel();
        return TransDocs.service.SpecificationService.specification(store, operation, viewModel);
    },

    lookupViewModel: function(){
        return this.getView().lookupViewModel();
    },

    applySpecification: function(store, operation){
        var me= this;
        store.setExtraParams({specification: me.specification(store, operation)});
    },

    findManager: function (combox, trigger, event) {
        var view = this.getView();
        var parentSession = view.lookupSession();
        var session = parentSession ? parentSession.spawn() : new Ext.data.Session();
        TransDocs.service.DictionaryService.openSearchDictionary("user_dictionary", view, this.dictionaryFindCallback, session, this, combox);
    },

    findCustomer: function (combox, trigger, event) {
        var view = this.getView();
        var parentSession = view.lookupSession();
        var session = parentSession ? parentSession.spawn() : new Ext.data.Session();
        TransDocs.service.DictionaryService.openSearchDictionary("customer_dictionary", view, this.dictionaryFindCallback, session, this, combox);
    },

    findCarrier: function (combox, trigger, event) {
        var view = this.getView();
        var parentSession = view.lookupSession();
        var session = parentSession ? parentSession.spawn() : new Ext.data.Session();
        TransDocs.service.DictionaryService.openSearchDictionary("carrier_dictionary", view, this.dictionaryFindCallback, session, this, combox);
    },

    dictionaryFindCallback: function(combo, value){
        var store = combo.getStore();
        if(value && value.length>0) {
            store.add(value[0])
            combo.select(value[0]);
        }
    },

    findDocuments: function () {
        var store = this.lookupJournalStore();
        store.reload();
    }
});