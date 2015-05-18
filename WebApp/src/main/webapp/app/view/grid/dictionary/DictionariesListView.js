Ext.define('TransDocs.view.grid.dictionary.DictionariesListView', {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    hideHeaders: true,
    alias:'widget.dictionaryList',
    title: 'Справочники',
    config : {
        width: 300,
        height: 300,
        enabled: true
    },
    requires:[
        'TransDocs.data.store.dictionary.DictionaryStore'
    ],
    store: {
        type: 'dictionaryStore'
    },

    features: [
        {
            ftype: 'grouping',
            groupHeaderTpl: Ext.create('Ext.XTemplate',
                '<div>{name:this.formatDescription} ({children.length})</div>',
                {
                    formatDescription: function(name) {
                        if(name=="company"){
                            return "Компания";
                        }else if(name=="contractor"){
                            return "Контрагенты";
                        }else if(name=="order"){
                            return "Заявка";
                        }else{
                            return name;
                        }
                    }
                }
            ),
            enableNoGroups:true
        }
    ],

    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        },
        {
            dataIndex: 'description',
            flex: 1
        }
    ]
});