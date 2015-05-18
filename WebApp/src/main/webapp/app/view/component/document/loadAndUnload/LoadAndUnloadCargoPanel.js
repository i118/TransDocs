Ext.define("TransDocs.view.component.document.loadAndUnload.LoadAndUnloadCargoPanel",{
    extend: "TransDocs.view.component.AbstractForm",
    alias: 'widget.loadAndUnloadCargoPanel',
    title: "Погр./Разгр.",
    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    requires: [
        "TransDocs.view.grid.document.LoadAndUnloadCargoGrid",
        "TransDocs.view.component.document.loadAndUnload.LoadAndUnloadInfoPanel"
    ],

    items:[
        {
            xtype: "fieldset",
            title: "Погр./Разгр.",
            layout: 'fit',
            flex: 1,
            items: [
                {
                    xtype: 'loadAndUnloadGrid'
                }
            ]
        },{
            xtype: "fieldset",
            layout: 'fit',
            flex: 2,
            items: [
                {
                    xtype: 'loadAndUnloadInfoPanel'
                }
            ]
        }
    ]


});