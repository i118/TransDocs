Ext.define("TransDocs.view.grid.dictionary.SimpleDictionaryGrid",{
    extend: "Ext.grid.Panel",
    alias: "widget.simpleDictionaryGrid",

    autoWidth: true,
    autoHeight: true,
    viewConfig : {
        getRowClass : function(record,id){
            if(record.get('deleted') == true){
                return 'hide-row';
            }
        }
    },
    bind: {store: "{simpleDictionary}"},

    columns:[
        {
            text: 'Наименование',
            dataIndex: 'description',
            flex: 1
        }
    ],

    listeners:{
        itemdblclick: "openDictionary"
    }
});