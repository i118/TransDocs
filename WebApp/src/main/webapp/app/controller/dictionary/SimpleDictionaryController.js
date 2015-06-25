Ext.define("TransDocs.controller.dictionary.SimpleDictionaryController", {
    extend: 'TransDocs.controller.dictionary.AbstractDictionaryController',
    alias: 'controller.simpleDictionaryController',

    requires: [
        "TransDocs.service.DictionaryService",
        "TransDocs.view.container.dictionary.SimpleDictionaryWindow"
    ],

    addDictionary: function(){
        var parentWnd = this.getView();
        var session = new  Ext.data.Session();
        var dictionaryType = parentWnd.getViewModel().get("dictionaryType");
        var dictionary = TransDocs.service.DictionaryService.newSimpleDictionary(dictionaryType, session);
        this.openDictionaryWnd(parentWnd, session, dictionary, true);
    },

    openDictionaryWnd: function(parentWnd, session, dictionary, isEditMode){
        var wnd = Ext.create("TransDocs.view.container.dictionary.SimpleDictionaryWindow",{
            session: session,
            reference: "simpleDictionaryWnd",
            viewModel: {
                session: session,
                data:{
                    isEditMode: isEditMode,
                    dictionary: dictionary
                }
            },
            parent: parentWnd
        });
        wnd.show();
    },

    cancelDictionary: function(){
        var dictionaryWnd = this.lookupReference("simpleDictionaryWnd");
        dictionaryWnd.close();
    },

    saveDictionary: function(){
        var dictionaryWnd = this.lookupReference("simpleDictionaryWnd");
        dictionaryWnd.setLoading(true,true);
        var viewModel = dictionaryWnd.getViewModel();
        var dictionary = viewModel.get("dictionary");
        var store = this.lookupDictionaryStore();
        if(dictionary.isDirty()){
            dictionary.save({
                success: function (batch) {
                    dictionaryWnd.close();
                    store.reload();
                },callback: function(success){
                    dictionaryWnd.setLoading(false);
                },failure:function(){
                    store.remove(dictionary);
                }
            });
        }else{
            dictionaryWnd.setLoading(false);
            dictionaryWnd.close();
        }

    },

    removeDictionary: function(){
        var dictionaryList = this.lookupReference("simpleDictionaryGrid");
        var wnd = this.getView();
        wnd.setLoading(true, true);
        var selected = this.getSelected();
        if (selected) {
            selected.set("deleted", true);
            var store = this.lookupDictionaryStore();
            store.save({
                success: function (batch) {
                    dictionaryList.getView().refresh();
                },callback: function(){
                    wnd.setLoading(false);
                },failure:function(){
                    selection[0].reject();
                }
            });
        }

    },

    openDictionary: function(grid, record){
        var parentWnd = this.getView();
        var session = new  Ext.data.Session();
        this.openDictionaryWnd(parentWnd, session, record, true);
    },

    lookupDictionaryStore: function(){
        return this.getView().getViewModel().getStore("simpleDictionary");
    },

    lookupTreeStore: function(){
        return null;
    },

    getSelected: function(){
        var dictionaryList = this.lookupReference("simpleDictionaryGrid");
        var selection = dictionaryList.getSelectionModel().getSelection();
        var selected;
        if (selection && selection.length > 0) {
            selected = selection[0];
        }

        return selected;
    }
});