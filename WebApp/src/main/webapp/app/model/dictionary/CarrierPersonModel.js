Ext.define("TransDocs.model.dictionary.CarrierPersonModel", {
    extend: 'TransDocs.model.dictionary.AbstractContractPersonModel',

    requires: [
        "TransDocs.model.dictionary.CarrierModel",
        'TransDocs.data.reader.DefaultJsonReader'
    ],

    fields: [
        {
            name: 'contractorId', reference: {
            type: 'TransDocs.model.dictionary.CarrierModel',
            association: 'CarrierPersonModelByContractorModel',
            role: 'contractor',
            inverse: 'persons'
        }
        }
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'CarrierController/get.persons'
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
        return 'td_carrier_person';
    }
});