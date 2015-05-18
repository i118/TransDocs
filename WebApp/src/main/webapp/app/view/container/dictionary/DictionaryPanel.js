Ext.define("TransDocs.view.container.dictionary.DictionaryPanel", {
    extend: "Ext.panel.Panel",
    layout: 'fit',
    title: 'Справочники',
    requires:[
      'TransDocs.view.grid.dictionary.DictionariesListView'
    ],
    items: [
        {
            xtype:"dictionaryList"
        }

    ]
});