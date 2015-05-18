Ext.define("TransDocs.service.CarrierService", {
    extend: 'Ext.Base',
    singleton: true,

    requires: [
        "TransDocs.model.dictionary.DriverModel",
        "TransDocs.model.dictionary.CarModel",
        "TransDocs.model.dictionary.CarrierModel",
        "TransDocs.model.dictionary.AccountDetails",
        "TransDocs.model.file.CarrierFileModel"
    ],

    constructor: function () {
        Ext.data.NodeInterface.decorate("TransDocs.model.file.CarrierFileModel");
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
    }
});