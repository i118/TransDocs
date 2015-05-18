Ext.define("TransDocs.view.tree.dictionary.CustomerTree",{
    extend: "Ext.tree.Panel",
    rootVisible: true,
    layout: 'fit',
    hideHeaders: true,
    autoScroll:true,
    alias: 'widget.customertree',
    bind: {
      store: '{customerTree}'
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
    ],

    constructor: function(){
        this.callParent(arguments);
    }
});