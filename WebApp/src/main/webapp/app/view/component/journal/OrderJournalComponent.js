Ext.define("TransDocs.view.component.journal.OrderJournalComponent", {
    extend: "Ext.panel.Panel",
    alias: "widget.orderJournalComponent",
    requires: [
        "TransDocs.view.grid.journal.OrderJournalListView",
        "TransDocs.view.component.journal.OrderJournalFilter"
    ],
    layout: 'fit',

    dockedItems: {
        xtype: 'orderJournalFilter',
        dock: 'top'
    },


    items: [
        {
            xtype: 'panel',
            border: false,
            layout: 'fit',
            items: [
                {
                    xtype: 'orderJournalListView',
                    reference: 'orderJournalListView'
                }
            ]
        }

    ]
});