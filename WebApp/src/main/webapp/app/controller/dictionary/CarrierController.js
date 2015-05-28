Ext.define("TransDocs.controller.dictionary.CarrierController",{
    extend: 'TransDocs.controller.dictionary.AbstractContractorController',
    alias: 'controller.carrierController',

    requires: [
        "TransDocs.service.CarrierService",
        "TransDocs.view.component.dictionary.CarrierInfoComponent",
        "TransDocs.data.store.dictionary.CarrierStore"
    ],

    createContractorInfoComponent: function(config){
        config.reference = "carrierinfo";
        var fileStore = config.record.getFileStore();
        fileStore.set("root", true);
        fileStore.set("expanded", false);
        fileStore.set("loaded", false);
        var fileTreeStore = Ext.create("TransDocs.data.store.file.FileTreeStore",{
            model: Ext.getClass(fileStore).getName()
        });
        fileTreeStore.setRoot(fileStore);
        fileTreeStore.setRootVisible(false);
        fileTreeStore.getProxy().setExtraParam("containerType", fileStore.get("objectType"));
        if(!config.record.isNew()) {
            config.record.drivers().on("load", function (driverStore, records) {
                if (!config.record.cars().isLoaded()) {
                    config.record.cars().load({
                        callback: function(records, operation, success){
                            if(!success)return;
                            for(var i in records){
                                var car = records[i];
                                car.drivers().each(function(driver){
                                    driverStore.getSession().getRecord("TransDocs.model.dictionary.DriverModel", driver.getId()).setCar(car);
                                });
                            }
                        }
                    });
                }
            });
        }
        var viewModel = {
            session: config.session,
            parent: null,
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
        return  Ext.create("TransDocs.view.component.dictionary.CarrierInfoComponent", config);
    },

    createNewContractor: function(session){
        return  TransDocs.service.CarrierService.newCarrier(session);
    },

    lookupContractorInfo: function(){
        return this.lookupReference('carrierinfo');
    },

    lookupContractorTree: function(){
        return this.lookupReference('carrierTree');
    },

    lookupContractContainer: function(){
        return this.lookupReference('carrierContainer');
    },

    lookupDictionaryPanel: function(){
        return this.lookupReference("carrierDictionaryPanel");
    },

    lookupStore: function(session){
       return Ext.create("TransDocs.data.store.dictionary.CarrierStore",{session: session});
    },

    lookupTreeStore: function(){
        return this.getViewModel().getStore("carrierTreeStore");
    }
});