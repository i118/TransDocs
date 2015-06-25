Ext.define("TransDocs.data.store.dictionary.CustomerStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    alias: 'store.customerStore',
    requires: [
        'TransDocs.model.dictionary.CustomerModel'
    ],
    model: 'TransDocs.model.dictionary.CustomerModel'
});