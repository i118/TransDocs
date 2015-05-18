Ext.define("TransDocs.data.store.dictionary.CarrierStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "CarrierController"
    },

    alias: 'store.carrierStore',
    requires: [
        'TransDocs.model.dictionary.CarrierModel'
    ],
    model: 'TransDocs.model.dictionary.CarrierModel'
});