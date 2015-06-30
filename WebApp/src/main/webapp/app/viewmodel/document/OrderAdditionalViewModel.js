Ext.define('TransDocs.viewmodel.document.OrderAdditionalViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.orderAdditional',

    requires: [
        "TransDocs.model.document.OrderAdditional",
        "TransDocs.data.store.dictionary.SimpleDictionaryStore",
        "TransDocs.model.document.OrderTransport"
    ],


    formulas:{
        orderAdditional :{
            bind: '{document.orderAdditional}',

            get: function(additional){
                return additional;
            }
        }
    },
    stores: {
        simpleDictionaryStore: {
            type: 'simpleDictionaryStore'
        }
    }
})