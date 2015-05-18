Ext.define("TransDocs.view.grid.journal.JournalListView", {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    hideHeaders: true,
    alias: 'widget.journalList',
    title: 'Журналы',
    config: {
        width: 300,
        height: 300,
        enabled: true
    },
    requires: [
        'TransDocs.data.store.JournalStore'
    ],
    store: {
        type: 'journalStore'
    },

    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        },
        {
            dataIndex: 'description',
            flex: 1
        }
    ]
});