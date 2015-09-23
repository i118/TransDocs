/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.controller.document.OrderAdditionalController', {
    extend: 'TransDocs.controller.document.AbstractDocumentController',
    alias: 'controller.orderadditional',


    initViewModel: function(viewModel){
        var document = viewModel.get("document");
        this.initSpinner(viewModel);
        if(!document.isNew()){
            document.getOrderTransport({reload:true});
        }

        if(!document.getOrderTransport()){
            var orderTransport = this.getSession().createRecord("OrderTransport");
            document.setOrderTransport(orderTransport);
        }

        if(!document.isNew()){
            document.getCustomerAdditionalCondition({reload:true});
            document.getCarrierAdditionalCondition({reload:true});
        }

        if(!document.getCustomerAdditionalCondition()){
            var additionalCondition = this.getSession().createRecord("OrderAdditionalCondition");
            document.setCustomerAdditionalCondition(additionalCondition);
        }

        if(!document.getCarrierAdditionalCondition()){
            var additionalCondition = this.getSession().createRecord("OrderAdditionalCondition");
            document.setCarrierAdditionalCondition(additionalCondition);
        }

        if(!document.getOrderAdditional()){
            var additional = this.getSession().createRecord("OrderAdditional");
            document.setOrderAdditional(additional);
        }
    },

    initSpinner: function(vm){
        var orderWindow = vm.getView().up('window');
        var spinner = Ext.create("TransDocs.view.Spinner");
        spinner.setViewModel(vm);
        spinner.setView(orderWindow);
        spinner.addBind("{{document.customerAdditionalCondition}");
        spinner.addBind("{document.carrierAdditionalCondition}");
        spinner.addBind("{document.orderTransport}");
        orderWindow.spinner = spinner;
    },

    findTransportType: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("transport_type", orderWindow,  session, this, combox);
    },

    findBorderCrossing: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("border_crossing", orderWindow,  session, this, combox);
    },

    findAdditionalService: function(combox, trigger, event){
        var view = this.getView();
        var orderWindow = view.up('window');
        var session = view.lookupSession().spawn();
        TransDocs.service.DictionaryService.openSearchDictionary("additional_service", orderWindow,  session, this, combox);
    },

    selectDriver: function(combo, record, eOpts){
        var viewModel = this.getViewModel();
        var transport = viewModel.get("document").getOrderTransport();
        var driver = record;
        if(driver) {
            transport.setDriver(driver);
            transport.set("driverPhone",driver.get("phone"));
            var driverPassport = transport.getDriverPassport();
            if(!driverPassport){
                driverPassport = this.getSession().createRecord("Passport");
                transport.setDriverPassport(driverPassport);
            }

            if(driver.getPassport()) {
                driverPassport.set("serial", driver.getPassport().get("serial"));
                driverPassport.set("number", driver.getPassport().get("number"));
                driverPassport.set("issuedPassport", driver.getPassport().get("issuedPassport"));
            }
            var car = driver.getCar();
            var carCombo = this.lookupReference("carComboBox");
            carCombo.select(car);
            carCombo.fireEvent("select", carCombo, car)
        }else{
            transport.setDriver(driver);
        }
    },

    selectCar: function(combo, record, eOpts){
        var viewModel = this.getViewModel();
        var transport = viewModel.get("document").getOrderTransport();
        var car = record;
        if(car) {
            transport.set("trailer", car.get("trailerBrand"));
        }
    },

    viewCar: function(combox, trigger, event){
        var selection = combox.getSelection();
        if (selection) {
            var additional = this.getView();
            var session = additional.lookupSession().spawn();
            var orderWindow = additional.up('window');
            TransDocs.service.CarrierService.openCar(selection, orderWindow, session, false);
        }
    },

    viewDriver: function(combox, trigger, event){
        var selection = combox.getSelection();
        if (selection) {
            var additional = this.getView();
            var session = additional.lookupSession().spawn();
            var orderWindow = additional.up('window');
            var carrier = this.getViewModel().get("document").getCarrier();
            TransDocs.service.CarrierService.openDriver(selection, orderWindow, session, carrier, false);
        }
    }
});