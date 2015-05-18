Ext.define("TransDocs.data.store.dictionary.UserRoleListViewStore",{
    extend: "TransDocs.data.store.AbstractListViewStore",
    model: 'TransDocs.model.dictionary.RoleModel',
    config:{
        controllerName: "RoleListViewController"
    },
    constructor: function(config){
        this.callParent(arguments);
    },

    initProxy: function(){
        this.proxy = {
            type: 'memory'
        }
    }
});