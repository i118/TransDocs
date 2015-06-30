Ext.define("TransDocs.service.CarrierService", {
    extend: 'Ext.Base',
    singleton: true,

    requires: [
        "TransDocs.model.dictionary.DriverModel",
        "TransDocs.model.dictionary.CarModel",
        "TransDocs.model.dictionary.CarrierModel",
        "TransDocs.model.dictionary.AccountDetails",
        "TransDocs.model.file.CarrierFileModel",
    ],

    constructor: function () {
        this.callParent(arguments);
    },

    newCarrier: function (session) {
        var newCarrier = session.createRecord('TransDocs.model.dictionary.CarrierModel');
        var accountDetails = session.createRecord('TransDocs.model.dictionary.AccountDetails');
        newCarrier.setAccountDetails(accountDetails);
        var carrierFileStore = session.createRecord('TransDocs.model.file.CarrierFileModel');
        carrierFileStore.set("fileType", "STORE");
        newCarrier.setFileStore(carrierFileStore);
        newCarrier.persons().blockLoadCounter=1;
        newCarrier.cars().blockLoadCounter=1;
        newCarrier.drivers().blockLoadCounter=1;
        return newCarrier;
    },

    newDriver: function (session ,driverClass) {
        var newDriver = session.createRecord(driverClass);
        var passport = session.createRecord("TransDocs.model.Passport");
        newDriver.setPassport(passport);
        return newDriver;
    },

    newCar: function (session, carClass) {
        return session.createRecord(carClass);
    },

    openCar: function(car, parentWnd, session, isEditMode, isCreateMode){
        var viewModel = {
            data: {
                car: car,
                isEditMode: isEditMode,
                isCreateMode: isCreateMode
            }
        }
        var wnd = Ext.create("TransDocs.view.container.dictionary.CarWindow", {
            autoWidth: true,
            autoHeight: true,
            parent: parentWnd,
            reference: 'carWindow',
            viewModel: viewModel,
            session:session
        });
        wnd.getViewModel().setData();
        wnd.show();
    },

    openDriver: function(driver, parentWnd, session, carrier, isEditMode, isCreateMode){
        var viewModel =  {
            data:{
                record: driver,
                contractor: carrier,
                isEditMode:isEditMode,
                isCreateMode: isCreateMode
            }
        };

        var carLoadCallback = function(){
            wnd.setLoading(false);
        };

        carrier.cars().setRemoteFilter(false);
        carrier.cars().filter("deleted", false);
        carrier.cars().on("load",carLoadCallback);
        var wnd = Ext.create("TransDocs.view.container.dictionary.DriverWindow", {
            autoWidth: true,
            autoHeight: true,
            parent: parentWnd,
            reference: 'driverWindow',
            session: session,
            viewModel: viewModel,
            listeners:{
                close: function(){
                    carrier.cars().clearFilter();
                    carrier.cars().removeListener("load", carLoadCallback);
                },
                activate: function(){
                    if(carrier.cars().isLoading()){
                        wnd.setLoading(true, true);
                    }
                }
            }
        });
        wnd.show();
    }
});