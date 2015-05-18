Ext.define("TransDocs.view.component.SimpleDictionaryPanel",{
    extend: "Ext.panel.Panel",
    alias: "widget.simpleDictionary",
    layout: "fit",

    requires:[
        "TransDocs.view.grid.dictionary.SimpleDictionaryGrid"
    ],

    tbar:[
        {
            text: "Добавить",
            handler: "addDictionary"
        },{
            text: "Удалить",
            handler: "removeDictionary"
        }
    ],

    items:[
        {
            xtype: "simpleDictionaryGrid",
            reference: "simpleDictionaryGrid"
        }
    ]
});