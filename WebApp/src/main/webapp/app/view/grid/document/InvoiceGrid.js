Ext.define("TransDocs.view.grid.document.InvoiceGrid", {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    alias: "widget.invoiceGrid",

    columns: [
        {
            text: "Номер",
            flex: 1
        }, {
            text: "Котрагент",
            flex: 1
        }, {
            text: "Сумма",
            flex: 1
        }, {
            text: "Статус",
            flex: 1
        }
    ]
});