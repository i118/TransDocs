Ext.define("TransDocs.controller.dictionary.DriverFormController", {
    extend: 'Ext.app.ViewController',
    alias: 'controller.driverFormController',

    closeDriverForm: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        if(!viewModel.get("saveMode")) {
            var driverForm = this.lookupReference("driverForm");
            var driver = viewModel.get("record");
            var contractor = viewModel.get("contractor");

            if (!driver.isNew()) {
                driver.reject();
            } else if (viewModel.get("isCreateMode")) {
                contractor.drivers().remove(driver);
            }
        }
    },

    cancel: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", false);
        view.close();
    },

    saveDriver: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", true);
        view.close();
    },

    selectCar: function(combo, car, eOpts){
        var driverForm = this.lookupReference("driverForm");
        var driver = driverForm.lookupViewModel().get("record");
        car.drivers().add(driver);
        driver.setCar(car);
    },

    viewCar: function(combox, trigger, event){
        var selection = combox.getSelection();
        if (selection) {
            var parentWnd = this.getView();
            var session = parentWnd.lookupSession().spawn();
            TransDocs.service.CarrierService.openCar(selection, this.getView(), session, false);
        }
    }
});
