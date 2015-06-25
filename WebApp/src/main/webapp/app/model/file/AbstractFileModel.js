Ext.define("TransDocs.model.file.AbstractFileModel",{
    extend: "TransDocs.model.tree.TreeBase",
    fields:[
        {name: 'extension', type:'string'},
        {name: 'fileType', type:'string'},
        {name: 'mimeType', type:'string'},
        {name: 'name', type:'string'},
        {name: 'fileLocation', type:'string'},
        {name: 'lockObject', defaultValue: null},
    ],

    actionMapEnabled: true,

    requires: [
        "TransDocs.actionifo.FileActionInfoMap"
    ],

    proxy: {
        type: 'rest',
        api:{
            read:  'FileController/get.files',
            destroy: "FileController/delete.file",
            load: 'FileController/load.file'
        },
        autoLoad: false,
        reader: {type: "defaultjson"},
        model: Ext.getClass(this),
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


    createActionMap: function(){
         return Ext.create("TransDocs.actionifo.FileActionInfoMap",{model: this});
    },

    reloadCallBack: function(){
        this.set("leaf", this.get("fileType") === "FILE");
        if (this.get("fileType") === "FILE") {
            this.set('iconCls', TransDocs.service.FileService.getIcon(this));
        }
    }

});