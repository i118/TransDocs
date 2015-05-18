Ext.define("TransDocs.view.container.dictionary.UserDictionaryPanel", {
    extend: "Ext.panel.Panel",
    alias: "widget.userDictionaryPanel",
    layout: {
        type: 'hbox',
        align: 'stretch'
    },



    requires: [
        'TransDocs.view.tree.dictionary.UserTree',
        'TransDocs.view.component.dictionary.UserInfoComponent'
    ],

    items: [
        {
            xtype: "usertree",
            flex: 1,
            width: 170,
            draggable: false,
            resizable: {
                handles: 'e',
                dynamic: true
            },
            reference: 'usertree',
            listeners: {
                itemclick: "loadUserInfo",
                selectionchange: "selectionChange"
            }
        },{
            xtype: "userinfo",
            reference: 'userinfo',
            flex: 2
        }
    ]
});