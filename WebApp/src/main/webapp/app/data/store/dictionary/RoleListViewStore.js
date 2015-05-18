Ext.define("TransDocs.data.store.dictionary.RoleListViewStore",{
    extend: "TransDocs.data.store.AbstractListViewStore",
    model: 'TransDocs.model.dictionary.RoleModel',
    alias: "store.roleStore",
    config:{
        controllerName: "RoleListViewController"
    }
});