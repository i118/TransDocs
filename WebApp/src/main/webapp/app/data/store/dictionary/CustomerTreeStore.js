Ext.define("TransDocs.data.store.dictionary.CustomerTreeStore", {
    extend: "TransDocs.data.store.AbstractTreeStore",
    alias: "store.customerTree",
    requires: [
        "TransDocs.data.proxy.dictionary.CustomerTreeProxy",
        'TransDocs.model.dictionary.CustomerModel'
    ],

    proxy: {
        type: "customerTree"
    },

    sorters: [{
        property: 'description',
        direction: 'ASC'
    }],

    rootVisible:true,

    model: 'TransDocs.model.dictionary.CustomerModel',

    sorters: [{
        property: 'description',
        direction: 'ASC'
    }],

    listeners: {
        load: function (store, records, successful, operation, node) {
            if (!successful) return;

            for (var index in records) {
                var record = records[index];
                if (record.get('deleted')) {
                    record.set('iconCls', 'tree-icon-delete');
                }
            }
        }
    },

    root: {
        description: "Клиенты",
        expanded: false,
        objectId: "td_customer"
    }
});