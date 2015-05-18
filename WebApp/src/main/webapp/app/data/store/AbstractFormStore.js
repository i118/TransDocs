Ext.define("TransDocs.data.store.AbstractFormStore", {
    extend: "Ext.data.Store",
    timeout: 180000,
    autoLoad: false,
    autoDestroy: true,
    config: {
        controllerName: ""
    },

    requires: [
        "TransDocs.data.reader.DefaultJsonReader",
        "TransDocs.data.writer.AssociationJsonWriter"
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

    constructor: function (config) {
        this.initConfig(config);
        this.initApi();
        this.callParent(arguments);
    },

    initApi: function () {
        var me = this;
        this.getProxy().setApi({
            create: me.controllerName + '/create.object',
            update: me.controllerName + '/update.object',
            destroy: me.controllerName + '/delete.object',
            read: me.controllerName + '/get.object'
        })
    },
    setExtraParams: function (params) {
        this.getProxy().setExtraParams(params);
    }
});