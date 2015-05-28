Ext.define("TransDocs.model.dictionary.DriverModel", {
    extend: 'TransDocs.model.AbstractModel',

    requires: [
        "TransDocs.model.dictionary.CarrierModel",
        "TransDocs.model.PassportInterface",
        "TransDocs.model.dictionary.CarModel"
    ],

    fields: [
        {name: 'firstName', type: 'string'},
        {name: 'lastName', type: 'string'},
        {name: 'patronymic', type: 'string'},
        {name: 'gender', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'registrationAddress', type: 'string'},
        {name: 'drivingLicense', type: 'string'},
        {
            name: 'defaultCarId', reference: {
            type: 'TransDocs.model.dictionary.CarModel',
            association: 'DriverModelByCarModel',
            role: 'car',
            inverse: 'drivers'
        }
        },
        {
            name: 'carrierId', reference: {
            type: 'TransDocs.model.dictionary.CarrierModel',
            association: 'DriverByContractorModel',
            role: 'carrier',
            inverse: 'drivers'
        }
        }
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'CarrierController/get.drivers'
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