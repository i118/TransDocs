Ext.define("TransDocs.model.dictionary.CustomerModel",{
    extend: 'TransDocs.model.dictionary.ContractorModel',
    requires: [
        'TransDocs.model.file.CustomerFileModel'
    ],

    fields: [
        {name: "files", persist:false},
        {name: "fileStoreId", reference: 'TransDocs.model.file.CustomerFileModel', unique:true}
    ],

    files: function(){
        return this.getFileStore().files();
    },

    getObjectType: function(){
        return  'td_customer';
    }
});