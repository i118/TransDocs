Ext.define("TransDocs.service.DictionaryFactory",{
    extend: 'Ext.Class',
    singleton: true,
    dictionaryTypeMap: Ext.create("Ext.util.HashMap"),

    constructor: function(config){
        this.callParent(arguments);
    },

    registerDictionary: function(dictionary){
        this.dictionaryTypeMap.add(dictionary.get("dictionaryType").trim().toLowerCase(), dictionary);
    },

    findByType: function(type){
       return this.dictionaryTypeMap.get(type);
    }
});
