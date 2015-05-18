Ext.define("TransDocs.data.store.dictionary.CustomerStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "CustomerController"
    },

    alias: 'store.customerStore',
    requires: [
        'TransDocs.model.dictionary.CustomerModel'
    ],
    model: 'TransDocs.model.dictionary.CustomerModel'
});