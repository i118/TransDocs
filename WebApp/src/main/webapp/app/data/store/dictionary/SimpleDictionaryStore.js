Ext.define("TransDocs.data.store.dictionary.SimpleDictionaryStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "SimpleDictionaryController"
    },

    alias: 'store.simpleDictionaryStore',
    requires: [
        'TransDocs.model.dictionary.SimpleDictionary'
    ],
    model: 'TransDocs.model.dictionary.SimpleDictionary'

});