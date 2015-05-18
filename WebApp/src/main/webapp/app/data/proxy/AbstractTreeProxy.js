Ext.define('TransDocs.data.proxy.AbstractTreeProxy', {
    extend: 'TransDocs.data.proxy.AbstractRestProxy',
    config: {
        controllerName: ""
    },
    appendId: false,
    paramsAsJson: true,
    actionMethods: {
        create: 'POST',
        read: 'POST',
        update: 'PUT',
        destroy: 'DELETE'
    },

    requires: [
        'TransDocs.data.reader.DefaultJsonReader',
        'TransDocs.data.writer.DefaultJsonWriter'
    ],
    reader: {
        type: "defaultjson"
    },
    writer: {
        type: "defaultjson"
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

    constructor: function (config) {
        this.initConfig(config);
        this.createApi();
        this.callParent(arguments);
    },

    createApi: function () {
        this.api = {
            read: this.controllerName + '/get.object'
        };
    }
});