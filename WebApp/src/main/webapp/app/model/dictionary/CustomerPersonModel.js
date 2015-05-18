Ext.define("TransDocs.model.dictionary.CustomerPersonModel", {
    extend: 'TransDocs.model.dictionary.AbstractContractPersonModel',

    requires: [
        "TransDocs.model.dictionary.CustomerModel",
        'TransDocs.data.reader.DefaultJsonReader'
    ],

    fields: [
        {
            name: 'contractorId', reference: {
            type: 'TransDocs.model.dictionary.CustomerModel',
            association: 'CustomerPersonModelByContractorModel',
            role: 'contractor',
            inverse: 'persons'
        }
        }
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'CustomerController/get.persons'
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
        return 'td_customer_person';
    }
});