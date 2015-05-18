Ext.define('TransDocs.controller.WorkSpaceController', {
    extend: 'Ext.app.Controller',

    requires: [
        "TransDocs.service.DictionaryComponentFactory",
        "TransDocs.service.JournalDocumentFactory"
    ],

    views: [
        'TransDocs.view.grid.dictionary.DictionariesListView',
        'TransDocs.view.grid.journal.JournalListView'
    ],
    stores: [
        'TransDocs.data.store.dictionary.DictionaryStore',
        'TransDocs.data.store.JournalStore'
    ],
    models: ['TransDocs.model.dictionary.Dictionary'],

    refs: [
        {
            ref: 'TransDocs.view.grid.dictionary.DictionariesListView',
            selector: 'dictionaryList'
        }, {
            ref: 'TransDocs.view.grid.journal.JournalListView',
            selector: 'journalList'
        }
    ],

    init: function () {
        this.control({
            'dictionaryList': {
                itemclick: this.openDictionary
            },'journalList': {
                itemclick: this.openJournal
            }
        });
    },

    openDictionary: function (grid, record) {
        var wndConf = TransDocs.service.DictionaryComponentFactory.getDictionaryComponent(record);
        TransDocs.util.WindowManager.openWindow(wndConf);

    },

    openJournal: function(grid, record){
        var wndConf = TransDocs.service.JournalDocumentFactory.getJournalComponent(record);
        TransDocs.util.WindowManager.openWindow(wndConf);
    }
});