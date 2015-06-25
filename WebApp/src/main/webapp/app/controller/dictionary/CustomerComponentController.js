Ext.define('TransDocs.controller.dictionary.CustomerComponentController', {
    extend: 'TransDocs.controller.dictionary.AbstractContractorController',
    alias: 'controller.customerComponentController',
    requires: [
        "TransDocs.service.CustomerService",
        "TransDocs.view.component.dictionary.CustomerInfoComponent",
        "TransDocs.data.store.dictionary.CustomerStore"
    ],



    createContractorInfoComponent: function(config){
        config.reference = "customerinfo";
        var fileStore = config.record.getFileStore();
        fileStore.set("root", true);
        fileStore.set("text", "fileStore");
        fileStore.set("expanded", false);
        fileStore.set("loaded", fileStore.isNew());
        var fileTreeStore = Ext.create("TransDocs.data.store.file.FileTreeStore",{
            model: Ext.getClass(fileStore).getName()
        });
        fileTreeStore.setRoot(fileStore);
        fileTreeStore.setRootVisible(false);
        fileTreeStore.getProxy().setExtraParam("containerType", fileStore.get("objectType"));
        var viewModel = {
            parent: null,
            session: config.session,
            data: {
                contractor: config.record,
                fileStore: config.record.getFileStore(),
                isEditMode: config.isEditMode
            },
            stores: {
                fileTreeStore: fileTreeStore,
                contractorStore: config.store
            }
        };
        config.viewModel = viewModel;
        return Ext.create('TransDocs.view.component.dictionary.CustomerInfoComponent', config);
    },

    createNewContractor: function(session){
        return  TransDocs.service.CustomerService.newCustomer(session);
    },

    lookupContractorInfo: function(){
        return this.lookupReference('customerinfo');
    },

    lookupContractorTree: function(){
        return this.lookupReference('customertree');
    },

    lookupContractContainer: function(){
        return this.lookupReference('customerContainer');
    },

    lookupDictionaryPanel: function(){
        return this.lookupReference("customerDictionaryPanel");
    },

    lookupStore: function(session){
        return Ext.create("TransDocs.data.store.dictionary.CustomerStore",{session: session});
    },

    lookupTreeStore: function(){
        return this.getViewModel().getStore("customerTree");
    }
});