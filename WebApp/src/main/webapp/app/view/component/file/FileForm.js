Ext.define("TransDocs.view.component.file.FileForm", {
    extend: 'TransDocs.view.component.AbstractForm',
    alias: "widget.fileForm",
    requires: [
        "TransDocs.view.grid.file.FileListView"
    ],
    layout: "fit",
    config: {
        modelClass: null
    },


    listeners: {
        activate: function () {
            var store = this.lookupViewModel().getStore('fileTreeStore');

            store.getRoot().expand();

//            if (store && store.isLoading()) {
//                this.setLoading(true, true);
//            }
        }
    },


    initComponent: function () {
        var me = this;
        this.items = [{
            xtype: 'fileListView',
            modelClass:me.getModelClass()
        }],
            this.callParent(arguments);
    },

    loadRecord: function (data) {
        this.model = data;
        var fileList = this.down("fileListView");
        if (fileList && !this.isLoaded) {
            this.on('activate', function () {
                fileList.loadRecord(this.model);
                this.isLoaded = true;
            }, this);
        }
    }
});