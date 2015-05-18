Ext.define("TransDocs.view.container.admin.CompanyPanel", {
    extend: "Ext.panel.Panel",
    alias: "widget.companyPanel",
    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    requires: [
        'TransDocs.view.container.admin.CompanyTree',
        'TransDocs.view.component.dictionary.ContractorContainer'
    ],

    items: [
        {
            xtype: 'companyTree',
            flex:1,
            width: 170,
            draggable: false,
            resizable: {
                handles: 'e',
                dynamic: true
            },
            reference:'companyTree',
            listeners: {
                itemclick: "loadContractorInfo",
                selectionchange: "selectionChange"
            }
        }, {
            xtype: 'contractorContainer',
            flex: 2,
            reference:'companyContainer'
        }
    ]
});
