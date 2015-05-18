Ext.define("TransDocs.view.component.document.OrderInvoicePanel",{
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.orderInvoicePanel",
    title: "Счета",
    requires: [
        "TransDocs.view.grid.document.InvoiceGrid"
    ],

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [
        {
            xtype: "fieldset",
            title: "Счета заказчикам",
            layout: 'fit',
            flex:1,
            items: [
                {
                    xtype: 'invoiceGrid',
                    tbar:[
                        {
                            text: "Добавить"
                        },{
                            text: "Удалить"
                        }
                    ]
                }
            ]
        },{
            xtype: "fieldset",
            title: "Счета перевозчиков",
            layout: 'fit',
            flex:1,
            items: [
                {
                    xtype: 'invoiceGrid',
                    tbar:[
                        {
                            text: "Добавить"
                        },{
                            text: "Удалить"
                        }
                    ]
                }
            ]
        }
    ]
});