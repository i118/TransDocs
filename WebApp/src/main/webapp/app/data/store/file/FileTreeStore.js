Ext.define('TransDocs.data.store.file.FileTreeStore', {
    extend: "TransDocs.data.store.AbstractTreeStore",
    alias: 'store.fileTreeStore',
    expanded: false,
    autoLoad: false,
    requires: [
        "TransDocs.data.proxy.file.FileTreeProxy"
    ],
    proxy: {
        type: "fileTreeProxy"
    },
    rootVisible: true,
    root: {
        text: 'root',
        id: 'root',
        expanded: true,
        loaded: true
    },
    listeners: {
        load: function (store, records, successful, operation, node) {
            if (!successful) return;

            for (var i in records) {
                var record = records[i];
                if (record) {
                    record.set("leaf", record.get("fileType") === "FILE");
                    if (record.get("fileType") === "FILE") {
                        record.set('iconCls', TransDocs.service.FileService.getIcon(record));
                    }
                    node.files().add(record);
                }
            }
        }, beforeload: function (store, operation, eOpts) {
            var id = operation.getId();
            if(id) {
                var node = store.getById(id);
                if (node) {
                    operation.getParams().containerType = node.get("objectType");
                }
            }
        }
    }
});