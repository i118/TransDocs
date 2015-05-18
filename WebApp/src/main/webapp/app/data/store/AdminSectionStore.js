Ext.define("TransDocs.data.store.AdminSectionStore",{
    extend: 'TransDocs.data.store.AbstractListViewStore',

    alias: 'store.adminStore',
    config:{
        controllerName: "AdminController"
    },

    fields:[
        {name: "objectId", type: "string"},
        {name: "description", type: "string"},
        {name: "sectionType", type: "string"}
    ],

    initProxy: function () {
        this.getProxy().setApi({
            read: this.controllerName+'/get.sections'
        })
    }
});