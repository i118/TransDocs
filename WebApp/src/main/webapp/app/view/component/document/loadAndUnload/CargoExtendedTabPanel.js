/**
 * Created by zerotul on 16.04.15.
 */
Ext.define('TransDocs.view.component.document.loadAndUnload.CargoExtendedTabPanel', {
    requires: [

        'TransDocs.view.component.document.loadAndUnload.CargoMainPanel',
        'TransDocs.view.component.document.loadAndUnload.CargoAdditionalPanel'
    ],
    extend: 'Ext.tab.Panel',
    alias: "widget.cargoExtendedPanel",



    items: [
        {
            xtype: "cargoMainPanel",
            title: "Груз"
        },{
            xtype: "cargoAdditionalPanel",
            title: "Дополнительно"
        }
    ]
});