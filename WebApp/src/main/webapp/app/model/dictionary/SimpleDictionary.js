Ext.define('TransDocs.model.dictionary.SimpleDictionary', {
    extend: 'TransDocs.model.AbstractModel',
    entityName: "SimpleDictionary",
    fields:[
        {name: 'description', type: 'string'},
        {name: 'dictionaryType', type: 'string'}
    ],

    getObjectType: function(){
        return "simple_dictionary";
    }
});
