Ext.define("TransDocs.model.dictionary.CustomerModel",{
    extend: 'TransDocs.model.dictionary.ContractorModel',
    requires: [
        'TransDocs.model.file.CustomerFileModel'
    ],

    fields: [
        {name: "files", persist:false}
    ],

    associations: [

        {
            type: 'hasOne',
            name:'fileStore',
            instanceName:'fileStore',
            model:'TransDocs.model.file.CustomerFileModel',
            getterName:'getFileStore',
            setterName:'setFileStore',
            associationKey:'fileStore',
            foreignKey:'fileStore'
        }
    ],

    files: function(){
        return this.getFileStore().files();
    },

    getObjectType: function(){
        return  'td_customer';
    }
});