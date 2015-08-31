Ext.define("TransDocs.model.dictionary.AccountDetails", {
    extend: "Ext.data.Model",

    constructor: function () {
        this.callParent(arguments);
        this.phantom = false;
    },

    fields: [
        {name: 'inn', type: 'string'},
        {name: 'kpp', type: 'string'},
        {name: 'bic', type: 'string'},
        {name: 'okpo', type: 'string'},
        {name: 'ogrn', type: 'string'},
        {name: 'okved', type: 'string'},
        {name: 'bank', type: 'string'},
        {name: 'director', type: 'string'},
        {name: 'chiefAccountant', type: 'string'},
        {name: 'account', type: 'string'},
        {name: 'correspondentAccount'},
        {
            name: 'objectType', type: 'string',critical:true, convert: function (value, record) {
            return 'account_details';
        }
        }
    ]
});