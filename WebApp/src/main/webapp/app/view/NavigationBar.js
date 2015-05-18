Ext.define('TransDocs.view.NavigationBar', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.navigationBar',
    region: 'west',
    requires: [
        'TransDocs.view.grid.dictionary.DictionariesListView',
        'TransDocs.view.grid.journal.JournalListView'
    ],
    layout: 'accordion',

    collapsible: true,
    split: true,
    width: 300,

    items: [
        {
            xtype: 'dictionaryList'
        }, {
            xtype: 'journalList'
        }
    ]
});