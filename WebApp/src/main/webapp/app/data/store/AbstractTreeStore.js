Ext.define('TransDocs.data.store.AbstractTreeStore', {
    extend: "Ext.data.TreeStore",
    autoLoad: false,
    nodeParam: "objectId",
    timeout: 180000,
    autoSave: false,
    autoDestroy: true,

    listeners: {
        load: function (store, records, successful, operation, node) {
            if (!successful) return;

            for (var index in records) {
                var record = records[index];
                if (record.get('deleted')) {
                    record.set('iconCls', 'tree-icon-delete');
                }
            }
        },
        beforeload: 'applySpecification'
    },
    setExtraParams: function (params) {
        this.getProxy().setExtraParams(params);
    }
});