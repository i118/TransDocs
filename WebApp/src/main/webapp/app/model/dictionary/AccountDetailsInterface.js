Ext.define("TransDocs.model.dictionary.AccountDetailsInterface",{
    statics: {
        decorate: function(modelClass){
            var model = Ext.data.schema.Schema.lookupEntity(modelClass);
            model.addFields([
                {name: 'inn', type: 'string', mapping:"accountDetails.inn"},
                {name: 'kpp', type: 'string', mapping:"accountDetails.kpp"},
                {name: 'bic', type: 'string', mapping:"accountDetails.bic"},
                {name: 'okpo', type: 'string', mapping:"accountDetails.okpo"},
                {name: 'ogrn', type: 'string', mapping:"accountDetails.ogrn"},
                {name: 'okved', type: 'string', mapping:"accountDetails.okved"},
                {name: 'bank', type: 'string', mapping:"accountDetails.bank"},
                {name: 'director', type: 'string', mapping:"accountDetails.director"},
                {name: 'chiefAccountant', type: 'string', mapping:"accountDetails.chiefAccountant"},
                {name: 'account', type: 'string', mapping:"accountDetails.account"},
                {name: 'correspondentAccount', type: 'string', mapping:"accountDetails.correspondentAccount"},
                {name: 'accountDetails.objectType', type: 'string', mapping:"accountDetails.objectType", convert: function(value, record){
                    return 'account_details';
                }}
            ]);
        }
    }
});