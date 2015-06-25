Ext.define("TransDocs.model.dictionary.CarrierModel",{
    extend: 'TransDocs.model.dictionary.ContractorModel',
    requires: [
        'TransDocs.model.dictionary.AccountDetailsInterface',
        'TransDocs.model.file.CarrierFileModel'
    ],
    entityName:"Carrier",

    fields: [
        {name: "files", persist:false},
        {name: "fileStoreId", reference: 'TransDocs.model.file.CarrierFileModel', unique:true}
    ],

    files: function(){
        return this.getFileStore().files();
    },

    getObjectType: function(){
        return  'td_carrier';
    }
});