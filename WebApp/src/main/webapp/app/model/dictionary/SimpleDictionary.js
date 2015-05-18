Ext.define('TransDocs.model.dictionary.SimpleDictionary', {
    extend: 'TransDocs.model.AbstractModel',
    fields:[
        {name: 'description', type: 'string'},
        {name: 'dictionaryType', type: 'string'}
    ],

    getObjectType: function(){
        return "simple_dictionary";
    }
});
