Ext.define("TransDocs.view.component.dictionary.CompanyUserPanel", {
    extend:"TransDocs.view.component.AbstractForm",
    alias: "widget.companyUserPanel",
    layout: "fit",
    requires:[
        'TransDocs.view.grid.dictionary.CompanyUserListView'
    ],

    items: [
        {
            xtype: 'companyUserListView',
            reference: "companyUserListView"
        }
    ],


    lookupStore: function () {
        return  this.lookupViewModel().get("contractor").users();
    }
});