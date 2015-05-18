Ext.define("TransDocs.view.component.document.loadAndUnload.CargoPanel",{
    requires: [
        'TransDocs.view.grid.document.CargoGrid',
        'TransDocs.view.component.document.loadAndUnload.CargoExtendedTabPanel'
    ],
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.cargoPanel",

    layout: {
        type: "vbox",
        align: 'stretch'
    },
    items: [
        {
            xtype: 'cargoGrid',
            flex: 1
        },{
            xtype: 'cargoExtendedPanel',
            flex: 2.6
        }
    ]

});