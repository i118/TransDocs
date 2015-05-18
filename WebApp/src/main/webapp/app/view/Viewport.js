Ext.define('TransDocs.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout: 'fit',
    id: 'TransDocsViewPort',
    requires: [
        'TransDocs.view.container.WorkSpace'
    ],
    items:[
        {
            xtype: 'workspace'
        }
    ]
});