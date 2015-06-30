Ext.define("TransDocs.controller.dictionary.DriverController",{
    extend: 'Ext.app.ViewController',
    alias: 'controller.driverController',

    requires: [
        "TransDocs.view.container.dictionary.DriverWindow",
        "TransDocs.service.CarrierService"
    ],

    addDriver: function(button){
        var parentWnd = button.up('window');
        var view = this.getView();
        var me = this;
        var driver = TransDocs.service.CarrierService.newDriver(view.lookupSession(), view.getModelClass());
        var contractor = view.lookupViewModel().get("contractor");
        var isEditMode = this.getView().lookupViewModel().get("isEditMode");
        contractor.drivers().add(driver);
        TransDocs.service.CarrierService.openDriver(driver, parentWnd, view.lookupSession().spawn(), contractor, isEditMode, true);
    },

    deleteDriver: function(){
        var driverList = this.lookupReference("driverListView");
        var selection = driverList.getSelectionModel().getSelection();
        if (selection && selection.length > 0) {
            var record = driverList.lookupViewModel().get("contractor");
            for (var index in selection) {
                if(!selection[index].isNew()) {
                    selection[index].set("deleted", true);
                }else{
                    record.drivers().remove(selection[index]);
                }
            }
        }
        driverList.getView().refresh();
    },

    openDriver: function(grid, record){
        var parentWnd = grid.up('window');
        var view = this.getView();
        var me = this;
        var contractor = this.getView().lookupViewModel().get("contractor");
        var isEditMode = this.getView().lookupViewModel().get("isEditMode");
        if(!record.getPassport()) {
            var passport = view.lookupSession().createRecord("Passport");
            record.setPassport(passport);
        }
        TransDocs.service.CarrierService.openDriver(record, parentWnd, view.lookupSession().spawn(), contractor, isEditMode);
    }

});