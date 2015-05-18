Ext.define("TransDocs.controller.dictionary.AbstractDictionaryController",{
    extend: 'Ext.app.ViewController',

    requires: ["TransDocs.service.SpecificationService"],

    specification: function (store, operation) {
        var viewModel = this.lookupViewModel();
        return TransDocs.service.SpecificationService.specification(store, operation, viewModel);
    },

    lookupViewModel: function(){
        return this.getView().lookupViewModel();
    },

    applySpecification: function(store, operation){
        var me= this;
        store.setExtraParams({specification: me.specification(store, operation)});
    },

    initViewModel: function(){
        this.callParent(arguments);
        var store = this.lookupTreeStore();
        if(store) {
            store.getRoot().expand();
        }
    },

    showDeletedDictionary: function (checkbox, newValue, oldValue) {
        var store = this.lookupTreeStore();
        var viewModel = this.getViewModel();
        if(newValue) {
            viewModel.set("restrictions.deleted.value", null);
        }else{
            viewModel.set("restrictions.deleted.value", false);
        }
        store.reload();
    },

    findObjects: function(){
        var store = this.lookupTreeStore();
        store.reload();
    },

    lookupTreeStore: function(){
        throw "unimplemented method";
    },

    getSelected: function(){
        throw "unimplemented method";
    }
});