Ext.define("TransDocs.model.dictionary.CarrierPersonModel", {
    extend: 'TransDocs.model.dictionary.AbstractContractPersonModel',

    entityName: "CarrierPerson",
    requires: [
        "TransDocs.model.dictionary.CarrierModel",
        'TransDocs.data.reader.DefaultJsonReader'
    ],

    fields: [
        {
            name: 'contractorId', reference: {
            type: 'Carrier',
            association: 'CarrierByContractor',
            role: 'contractor',
            inverse: 'persons'
        }
        }
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'Carrier/get.persons'
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
        return 'td_carrier_person';
    }
});