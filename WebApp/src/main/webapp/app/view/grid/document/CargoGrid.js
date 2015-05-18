/**
 * Created by zerotul on 16.04.15.
 */
Ext.define('TransDocs.view.grid.document.CargoGrid', {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    alias: "widget.cargoGrid",


    columns: [
        {
            text: "Наименование",
            flex: 2
        }, {
            text: "Масса Брутто",
            flex: 1
        }, {
            text: "Кол-во мест",
            flex: 1
        }, {
            text: "Готовность",
            flex: 1
        }
    ]
});