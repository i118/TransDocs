Ext.define("TransDocs.view.container.dictionary.CustomerDictionaryPanel", {
    extend: "Ext.panel.Panel",
    alias: "widget.customerDictionaryPanel",
    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    requires: [
        'TransDocs.view.tree.dictionary.CustomerTree',
        'TransDocs.view.component.dictionary.ContractorContainer'
    ],

    items: [
        {
            xtype: 'customertree',
            flex:1,
            width: 170,
            draggable: false,
            resizable: {
                handles: 'e',
                dynamic: true
            },
            reference:'customertree',
            listeners: {
                itemclick: "loadContractorInfo",
                selectionchange: "selectionChange"
            }
        }, {
            xtype: 'contractorContainer',
            flex: 2,
            reference:'customerContainer'
        }
    ]
});
