Ext.define("TransDocs.view.component.document.OrderDocumentComponent", {
    requires: [
        'TransDocs.view.component.document.OrderMainPanel',
        'TransDocs.view.component.document.OrderAdditionalPanel',
        'TransDocs.view.component.document.loadAndUnload.LoadAndUnloadCargoPanel',
        'TransDocs.view.component.document.OrderInvoicePanel'
    ],
    extend: "TransDocs.view.component.AbstractComponent",
    alias: "widget.orderDocumentComponent",

    autoScroll:true,

    layout: 'fit',
    bbar: [
        '->', {
            text: 'Отмена',
            handler: 'closeOrder'
        }, {
            text: 'Печать'
        }, {
            text: "Сохранить",
            handler: 'saveAndCloseOrder'
        }

    ],

    items:[
        {
            xtype: "orderMainPanel",
            reference: 'orderMainPanel'
        },{
            xtype: "orderAdditionalPanel",
            reference: 'orderAdditionalPanel'
        },{
            xtype: "loadAndUnloadCargoPanel",
            reference: 'loadAndUnloadCargoPanel'
        },{
            xtype: "orderInvoicePanel",
            reference: 'orderInvoicePanel'
        }
    ]

});