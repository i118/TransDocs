Ext.define("TransDocs.data.store.document.OrderJournalStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    alias: 'store.orderJournalStore',

    remoteSort: true,

    autoLoad: false,

    fields: [
        {name: "incomingNumber", type: "string"},
        {name: "outgoingNumber", type: "string"},
        {name: "objectId", type: "string"},
        {name: "transportationType", type: "string"},
        {name: "customerFullName", type: "string"},
        {name: "carrierFullName", type: "string"},
        {name: "managerFullName", type: "string"}
    ],

    proxy: {
        type: 'rest',
        timeout: 180000,
        appendId: true,
        idParam: "objectId",
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        paramsAsJson: true,
        actionMethods: {
            create: 'POST',
            read: 'POST',
            update: 'PUT',
            destroy: 'DELETE'
        },
        api:{
            read: 'OrderDocument/find.dataset'
        },
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
        },
        reader: {
            type: "defaultjson"
        },
        writer: {
            type: "associationJsonWriter"
        }
    },

    setExtraParams: function(params){
        this.extraParams = params;
        this.getProxy().setExtraParams(this.extraParams);
    }
});