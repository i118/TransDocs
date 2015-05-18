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
        var viewModel =  {
            data:{
                contractor: contractor,
                record:driver,
                isEditMode: isEditMode
            }
        }
        var wnd = Ext.create("TransDocs.view.container.dictionary.DriverWindow", {
            autoWidth: true,
            autoHeight: true,
            parent: parentWnd,
            contractor: view.getModel(),
            reference: 'driverWindow',
            parentController: me,
            modelClass: view.getModelClass(),
            session: view.lookupSession().spawn(),
            viewModel: viewModel,
            createMode: true,
            listeners:{
                close: function(){
                    contractor.cars().clearFilter();
                }
            }
        });

        contractor.cars().setRemoteFilter(false);
        contractor.cars().filter("deleted", false);
        wnd.show();
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
        var viewModel =  {
            data:{
                record: record,
                contractor: contractor,
                isEditMode:isEditMode
            }
        };

        var carLoadCallback = function(){
            wnd.setLoading(false);
        };

        contractor.cars().setRemoteFilter(false);
        contractor.cars().filter("deleted", false);
        contractor.cars().on("load",carLoadCallback);
        var wnd = Ext.create("TransDocs.view.container.dictionary.DriverWindow", {
            autoWidth: true,
            autoHeight: true,
            parent: parentWnd,
            contractor: view.getModel(),
            reference: 'driverWindow',
            parentController: me,
            modelClass: view.getModelClass(),
            session: view.lookupSession().spawn(),
            viewModel: viewModel,
            listeners:{
                close: function(){
                    contractor.cars().clearFilter();
                    contractor.cars().removeListener("load", carLoadCallback);
                },
                activate: function(){
                    if(contractor.cars().isLoading()){
                        wnd.setLoading(true, true);
                    }
                }
            }
        });


        wnd.show();
    }

});