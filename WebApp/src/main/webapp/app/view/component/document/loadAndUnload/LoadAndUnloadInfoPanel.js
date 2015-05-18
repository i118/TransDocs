Ext.define("TransDocs.view.component.document.loadAndUnload.LoadAndUnloadInfoPanel",{
    extend: "Ext.tab.Panel",
    alias: 'widget.loadAndUnloadInfoPanel',
    requires: [
        "TransDocs.view.component.document.loadAndUnload.CargoPanel",
        "TransDocs.view.component.document.loadAndUnload.LoadCargoPanel",
        "TransDocs.view.component.document.loadAndUnload.UnloadCargoPanel",
        "TransDocs.view.component.document.loadAndUnload.AdditionalCargoPanel",
    ],

    items:[
        {
            xtype: "loadCargoPanel",
            title: "Погрузка"
        }, {
            xtype: "unloadCargoPanel",
            title: "Разгрузка"
        }, {
            xtype: "additionalCargoPanel",
            title: "Дополнительно"
        }, {
            xtype: "cargoPanel",
            title: "Груз"
        }
    ]


});