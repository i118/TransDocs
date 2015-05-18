Ext.define('TransDocs.view.AdminNavigationBar', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminNavigationBar',
    region: 'west',
    layout: 'fit',

    collapsible: true,
    split: true,
    width: 300,
    requires: [
        "TransDocs.view.grid.admin.AdminSectionListView"
    ],

    items:[
        {
            xtype: "adminSectionList"
        }
    ]
});