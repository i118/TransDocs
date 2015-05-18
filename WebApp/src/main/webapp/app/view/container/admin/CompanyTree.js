Ext.define("TransDocs.view.container.admin.CompanyTree",{
    extend: "Ext.tree.Panel",
    rootVisible: true,
    layout: 'fit',
    hideHeaders: true,
    autoScroll:true,
    alias: 'widget.companyTree',
    bind:{
        store: '{companyTreeStore}'
    },
    tbar:[
        {
            xtype:"textfield",
            bind: '{restrictions.description.value}'
        },{
            text: "Поиск",
            handler: "findObjects"
        }
    ],
    columns:[
        {
            xtype:'treecolumn',
            dataIndex: 'description',
            flex: 1
        }
    ]
});