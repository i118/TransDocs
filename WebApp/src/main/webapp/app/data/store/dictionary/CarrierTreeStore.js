Ext.define("TransDocs.data.store.dictionary.CarrierTreeStore", {
    extend: "TransDocs.data.store.AbstractTreeStore",

    alias: "store.carrierTree",
    requires: [
        "TransDocs.data.proxy.dictionary.CarrierTreeProxy",
        'TransDocs.model.dictionary.CarrierModel'
    ],

    proxy: {
        type: "carrierTree"
    },

    sorters: [{
        property: 'description',
        direction: 'ASC'
    }],
    rootVisible:true,
    model: 'TransDocs.model.dictionary.CarrierModel',

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
        description: "Перевозчики",
        expanded: false,
        objectId: "td_carrier"
    }
});