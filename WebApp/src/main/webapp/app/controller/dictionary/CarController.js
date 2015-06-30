Ext.define("TransDocs.controller.dictionary.CarController", {
    extend: 'Ext.app.ViewController',
    alias: 'controller.carController',

    requires: [
        'TransDocs.view.container.dictionary.CarWindow',
        "TransDocs.service.CarrierService"
    ],

    addCar: function(button){
        var parentWnd = button.up('window');
        var view = this.getView();
        var me = this;
        var car = TransDocs.service.CarrierService.newCar(view.lookupSession(), view.getModelClass());
        var contractor = view.lookupViewModel().get("contractor");
        contractor.cars().add(car);
        var isEditMode = view.lookupViewModel().get("isEditMode");
        TransDocs.service.CarrierService.openCar(car,parentWnd, view.lookupSession().spawn(), isEditMode, true)
    },

    deleteCar: function(){
        var carList = this.lookupReference("carListView");
        var selection = carList.getSelectionModel().getSelection();
        if (selection && selection.length > 0) {
            var record = carList.lookupViewModel().get("contractor");
            for (var index in selection) {
                if(!selection[index].isNew()) {
                    selection[index].set("deleted", true);
                }else{
                    record.cars().remove(selection[index]);
                }
                selection[index].drivers().each(function(driver){
                    driver.setCar(undefined);
                    record.drivers().on("load", function(store, records, successful, eOpts){
                        var loadedDriver = store.getById(driver.getId());
                        if(loadedDriver){
                            loadedDriver.setCar("");
                        }
                    });
                });

            }
        }
        carList.getView().refresh();
    },

    openCar: function(grid, record){
        var parentWnd = grid.up('window');
        var view = this.getView();
        var isEditMode = view.lookupViewModel().get("isEditMode");
        TransDocs.service.CarrierService.openCar(record, parentWnd, view.lookupSession().spawn(), isEditMode);
    }
});