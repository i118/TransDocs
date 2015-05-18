Ext.define("TransDocs.data.store.AbstractListViewStore", {
    extend: 'Ext.data.Store',
    config: {
        controllerName: ""
    },
    autoDestroy: true,
    autoLoad: true,
    timeout: 180000,

    requires: [
        "Ext.data.proxy.Rest",
        "TransDocs.data.reader.DefaultJsonReader"
    ],

    proxy: {
        type: 'rest',
        reader: {
            type: 'defaultjson'
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
        }
    },

    constructor: function (config) {
        this.callParent(arguments);
        this.initProxy();
    },

    initProxy: function () {
        this.getProxy().setApi({
            read: this.controllerName+'/get.content'
        })
    }
});