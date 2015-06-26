Ext.define("TransDocs.model.dictionary.DriverModel", {
    extend: 'TransDocs.model.AbstractModel',

    entityName: "Driver",
    requires: [
        "TransDocs.model.dictionary.CarrierModel",
        "TransDocs.model.Passport",
        "TransDocs.model.dictionary.CarModel"
    ],

    fields: [
        {name: 'firstName', type: 'string'},
        {name: 'lastName', type: 'string'},
        {name: 'patronymic', type: 'string'},
        {name: 'description', type: 'string'},
        {name: 'gender', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'registrationAddress', type: 'string'},
        {name: 'drivingLicense', type: 'string'},
        {name: "passport", reference: "TransDocs.model.Passport"},
        {
            name: 'defaultCarId', reference: {
            type: 'Car',
            association: 'DriverByCar',
            role: 'car',
            inverse: 'drivers'
        }
        },
        {
            name: 'carrierId', reference: {
            type: 'Carrier',
            association: 'DriverByContractor',
            role: 'carrier',
            inverse: 'drivers'
        }
        }
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'Carrier/get.drivers'
        },
        actionMethods: {
            read: 'get'
        },
        reader: {type: 'defaultjson'},
        listeners: {
            exception: function (proxy, response, operation) {
                Ext.MessageBox.show({
                    title: 'Error!',
                    msg: operation.getError() ? operation.getError() : "Ошибка связи с сервером",
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK,
                    resizable: true,
                    overflowY: 'auto',
                    overflowX: 'auto'
                });
            }
        }
    },

    getObjectType: function () {
        return "td_driver";
    }
});