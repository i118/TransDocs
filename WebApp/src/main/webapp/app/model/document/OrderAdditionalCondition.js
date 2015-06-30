/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.model.document.OrderAdditionalCondition', {
    extend: 'TransDocs.model.AbstractModel',

    entityName: "OrderAdditionalCondition",
    fields: [
        {name: "additionalCondition", type:"string"},
        {name: "penalty", type:"string"},
        {name: "agreementContent", type:"string"}
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'OrderDocument/get.additionalCondition'
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

    getObjectType: function(){
        return "order_additional_condition";
    }
});