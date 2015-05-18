Ext.define('TransDocs.data.store.dictionary.DictionaryStore', {
    extend: "TransDocs.data.store.AbstractListViewStore",
    model: 'TransDocs.model.dictionary.Dictionary',
    requires: [
        "TransDocs.model.dictionary.Dictionary"
    ],
    alias: 'store.dictionaryStore',
    config:{
        controllerName: "DictionaryListViewController"
    },

    groupField: "category",

    listeners: {
        load: function(store, records, successful, eOpts){
           for(var i in records){
               TransDocs.service.DictionaryFactory.registerDictionary(records[i]);
           }
        }
    }
});