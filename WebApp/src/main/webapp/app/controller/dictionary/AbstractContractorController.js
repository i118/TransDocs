Ext.define('TransDocs.controller.dictionary.AbstractContractorController', {
    extend: 'TransDocs.controller.dictionary.AbstractDictionaryController',

    loadContractorInfo: function (dataview, record, item, index, event) {
        if (record.isRoot()) return;
        var contractorInfo = this.lookupContractorInfo();
        if (contractorInfo) {
            contractorInfo.close();
        }
        var container = this.lookupContractContainer();
        container.setLoading(true, true);
        var me =this;
        var session =  new Ext.data.Session();
        var store = this.lookupStore(session);
        store.load({
            id: record.getId(),
            callback:function(records, operation, success){
                if(success){
                    var contractor = records[0];
                    var component =me.createContractorInfoComponent({
                        record: contractor,
                        visible: true,
                        session: session,
                        store: store
                    });

                    component.disable();
                    container.add(component);
                }
                container.setLoading(false);
            }
        })

    },

    selectionChange: function(model, selected){
        var contractorContainer = this.lookupContractContainer();
        if(contractorContainer){
            contractorContainer.changeButtonState(selected);
        }
    },

    addContractor: function (button) {
        var contractorInfo = this.lookupContractorInfo();
        if (contractorInfo) {
            contractorInfo.close();
        }
        var session =  new Ext.data.Session();
        var newContractor = this.createNewContractor(session);
        var store = this.lookupStore(session);
        store.add(newContractor);
        var component = this.createContractorInfoComponent({record: newContractor, visible: false, session:session, store: store, isEditMode: true});
        var container = this.lookupContractContainer();
        container.changeButtonState(newContractor);
        container.add(component);
        var contractorTree = this.lookupContractorTree();
        if(contractorTree.getSelectionModel() && contractorTree.getSelectionModel().hasSelection()){
            contractorTree.getSelectionModel().deselectAll()
        }
        component.show();
    },

    editContractor: function (button) {
        var contractorInfo = this.lookupContractorInfo();
        contractorInfo.enable();
    },

    deleteContractor: function(button){
        var contractorInfo = this.lookupContractorInfo();
        var viewModel = contractorInfo.lookupViewModel();
        var record = viewModel.get("contractor");
        if(record){
            record.set("deleted", true);
            var me = this;
            if(!record.isNew()) {
                var store = viewModel.getStore("contractorStore");
                contractorInfo.getStore().save({
                    success: function (batch) {
                        var contractorTree = me.lookupContractorTree();
                        contractorTree.getStore().reload();
                        contractorInfo.close();
                    }
                });
            }
        }
    },

    restoreContractor: function(button){
        var contractorInfo = this.lookupContractorInfo();
        var viewModel = contractorInfo.lookupViewModel();
        var contractor = viewModel.get("contractor");
        if(contractor){
            contractor.set('deleted', false);
            var me = this;
            var store = viewModel.getStore("contractorStore");
            contractorInfo.getStore().save({
                success: function(batch){
                    var contractorTree = me.lookupContractorTree();
                    contractorTree.getStore().reload();
                }
            });
        }
    },


    saveContractor: function (button) {
        var contractorInfo = this.lookupContractorInfo();
        var contractorPanel = this.lookupDictionaryPanel();
        contractorPanel.setLoading(true, true);
        var me = this;
        var contractor = contractorInfo.lookupViewModel().get("contractor");
        contractor.setDirty(true);
        var contractorStore = contractorInfo.lookupViewModel().getStore("contractorStore");
        contractorStore.save({
            success: function (batch) {
                var contractorTree = me.lookupContractorTree();
                contractorTree.getStore().reload();
                contractorInfo.close();
            },callback: function(){
                contractorPanel.setLoading(false);
            }
        });
    },


    cancelContractor: function (button) {
        var contractorInfo = this.lookupContractorInfo();
        contractorInfo.cancelEditModel();
        contractorInfo.close();
    },

    getSelected: function(){
        var tree = this.lookupContractorTree();
        var selection = tree.getSelection();
        var selected;
        if (selection && selection.length > 0) {
            selected = selection[0];
        }

        return selected;
    },

    /**
     Абстрактные функции необходимо переобределить в потомках
     */
    createContractorInfoComponent: function(config){
        throw "unimplemented method";
    },

    createNewContractor: function(session){
        throw "unimplemented method";
    },

    lookupContractorInfo: function(){
        throw "unimplemented method";
    },

    lookupContractorTree: function(){
        throw "unimplemented method";
    },

    lookupContractContainer: function(){
        throw "unimplemented method";
    },

    lookupDictionaryPanel: function(){
        throw "unimplemented method";
    },

    lookupStore: function(session){
        throw "unimplemented method";
    }
});