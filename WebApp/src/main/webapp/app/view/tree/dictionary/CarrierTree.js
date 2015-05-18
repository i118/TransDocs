Ext.define("TransDocs.view.tree.dictionary.CarrierTree",{
    extend: "Ext.tree.Panel",
    rootVisible: true,
    layout: 'fit',
    hideHeaders: true,
    autoScroll:true,
    alias: 'widget.carrierTree',
    bind:{
      store: '{carrierTreeStore}'
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