Ext.define('TransDocs.data.store.JournalStore', {
    extend: "TransDocs.data.store.AbstractListViewStore",
    alias: 'store.journalStore',
    config:{
        controllerName: "JournalListViewController"
    },

    fields: [
        {name: "objectId", type: "string"},
        {name: "journalType", type: "string"},
        {name: "description", type: "string"},
    ]
});