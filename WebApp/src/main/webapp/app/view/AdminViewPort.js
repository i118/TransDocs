Ext.define('TransDocs.view.AdminViewPort', {
    extend: 'Ext.container.Viewport',
    layout: 'fit',
    id: 'TransDocsViewPort',
    requires: [
        'TransDocs.view.container.AdminWorkSpace'
    ],
    items:[
        {
            xtype: 'adminworkspace'
        }
    ]
});