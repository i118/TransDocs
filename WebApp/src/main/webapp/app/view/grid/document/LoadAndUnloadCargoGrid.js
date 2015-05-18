Ext.define("TransDocs.view.grid.document.LoadAndUnloadCargoGrid",{
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    alias: 'widget.loadAndUnloadGrid',

    columns: [
        {
            text: "Погрузка",
            flex:1
        },{
            text: "Место",
            flex:1
        },{
            text: "Грузоотправитель",
            flex:1
        },{
            text: "Разгрузка",
            flex:1
        },{
            text: "Место",
            flex:1
        },{
            text: "Грузополучатель",
            flex:1
        },
    ]
});