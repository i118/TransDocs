Ext.define('TransDocs.model.dictionary.Dictionary', {
    extend: 'TransDocs.model.AbstractModel',
    fields: [
        {name: 'description', type: 'string'},
        {name: 'dictionaryType', type: 'string'},
        {name: 'category', type: 'string'}
    ],

    getObjectType: function(){
        return "";
    }
});