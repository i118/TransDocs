Ext.define("TransDocs.model.dictionary.CompanyModel",{
    extend: 'TransDocs.model.dictionary.ContractorModel',
    entityName: "Company",
    fields:[
        {name: 'leaf', type:"boolean", convert: function(value, record){
            return record.get("objectId")!=record.getObjectType();
        }, persist:false}
    ],

    getObjectType: function(){
        return  'td_company';
    }
});
