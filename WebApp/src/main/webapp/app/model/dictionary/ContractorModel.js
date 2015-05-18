Ext.define("TransDocs.model.dictionary.ContractorModel", {
    extend: 'TransDocs.model.AbstractModel',

    requires: [
        'TransDocs.model.dictionary.AccountDetails'

    ],

    fields: [
        {name: 'description', type: 'string'},
        {name: 'comment', type: 'string'},
        {name: 'email', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'mailingAddress', type: 'string'},
        {name: 'legalAddress', type: 'string'},
        {name: 'legalForm', type: 'string'},
        {name: 'shortName', type: 'string'},
        {name: 'fullName', type: 'string'},
        {name: 'accountDetails', reference: 'TransDocs.model.dictionary.AccountDetails', unique: true},
        {name: 'leaf', type:"boolean", convert: function(value, record){
            return record.get("objectId")!=record.getObjectType();
        }, persist:false}
    ],

    getObjectType: function () {
        return 'td_contractor';
    }

});