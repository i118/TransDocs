Ext.define("TransDocs.view.tree.dictionary.UserTree", {
    extend: "Ext.tree.Panel",
    rootVisible: true,
    layout: 'fit',
    hideHeaders: true,
    autoScroll:true,
    alias: 'widget.usertree',
    requires: [
        "TransDocs.model.dictionary.UserModel",
    ],

    bind: {
        store: "{userTree}"
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