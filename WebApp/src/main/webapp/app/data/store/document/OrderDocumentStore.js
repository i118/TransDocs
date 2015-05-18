Ext.define("TransDocs.data.store.document.OrderDocumentStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "OrderDocumentController"
    },


    alias: 'store.orderDocumentStore',
    requires: [
        'TransDocs.model.document.OrderDocumentModel'
    ],
    model: 'TransDocs.model.document.OrderDocumentModel',


});