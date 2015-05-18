Ext.define('TransDocs.data.proxy.file.FileTreeProxy',{
    extend: "TransDocs.data.proxy.AbstractTreeProxy",
    alias: "proxy.fileTreeProxy",
    autoLoad: false,
    paramsAsJson: false,
    appendId: true,
    actionMethods: {
        create: 'POST',
        read: 'GET',
        update: 'PUT',
        destroy: 'DELETE'
    },

    createApi: function(){
        this.api = {
            read:  'FileController/get.files',
            destroy: "FileController/delete.file"
        }
    }
});