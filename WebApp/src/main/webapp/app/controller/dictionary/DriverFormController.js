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
            } else if (view.createMode) {
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

    selectCar: function(combo, records, eOpts){
        var driverForm = this.lookupReference("driverForm");
        var driver = driverForm.lookupViewModel().get("record");
        records[0].drivers().add(driver);
        driver.setCar(records[0]);
    }
});
