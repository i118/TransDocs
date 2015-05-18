Ext.define("TransDocs.view.container.dictionary.CarrierDictionaryPanel", {
    extend: "Ext.panel.Panel",
    alias: "widget.carrierDictionaryPanel",
    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    requires: [
        'TransDocs.view.tree.dictionary.CarrierTree',
        'TransDocs.view.component.dictionary.ContractorContainer'
    ],

    items: [
        {
            xtype: 'carrierTree',
            flex:1,
            width: 170,
            draggable: false,
            resizable: {
                handles: 'e',
                dynamic: true
            },
            reference:'carrierTree',
            listeners: {
                itemclick: "loadContractorInfo",
                selectionchange: "selectionChange"
            }
        }, {
            xtype: 'contractorContainer',
            flex: 2,
            reference:'carrierContainer'
        }
    ]
});
