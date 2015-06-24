Ext.define("TransDocs.service.DictionaryService",{
    extend: 'Ext.Class',
    singleton: true,

    requires: [
        "TransDocs.view.container.dictionary.SearchDictionaryWindow",
        "TransDocs.model.dictionary.SimpleDictionary"
    ],

    constructor: function(config){
        this.callParent(arguments);
    },

    openSearchDictionary: function(dictionaryType, parenWnd,  session, scope, caller){
        var dictionary = TransDocs.service.DictionaryFactory.findByType(dictionaryType);
        if(!dictionary)return;
        var wndConf = TransDocs.service.DictionaryComponentFactory.getDictionaryComponent(dictionary);;
        wndConf.parent = parenWnd;
        wndConf.session = session;
        wndConf.scope = scope;
        wndConf.caller = caller
        var wnd = Ext.create("TransDocs.view.container.dictionary.SearchDictionaryWindow", wndConf);
        wnd.show();
    },

    newSimpleDictionary: function(dictionaryType, session){
        var dictionary = session.createRecord("TransDocs.model.dictionary.SimpleDictionary");
        dictionary.set("dictionaryType", dictionaryType);
        return dictionary;
    }
});