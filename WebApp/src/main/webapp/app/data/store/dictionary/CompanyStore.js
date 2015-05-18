Ext.define("TransDocs.data.store.dictionary.CompanyStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "CompanyController"
    },

    alias: 'store.companyStore',
    requires: [
        'TransDocs.model.dictionary.CompanyModel'
    ],
    model: 'TransDocs.model.dictionary.CompanyModel'
});