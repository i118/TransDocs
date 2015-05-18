Ext.define("TransDocs.view.grid.journal.OrderJournalListView",{
    extend: 'Ext.grid.Panel',
    alias: "widget.orderJournalListView",
    bind: {
        store: '{orderStore}'
    },
    requires: [
        "TransDocs.service.RenderFactory"
    ],

    listeners: {
        itemdblclick: 'openDocument'
    },

    autoScroll:true,

    dockedItems: [{
        xtype: 'pagingtoolbar',
        bind: {
            store: '{orderStore}'
        },
        dock: 'bottom',
        displayInfo: true,
        displayMsg: 'Показано {0} - {1} из {2}',
        emptyMsg: "Нет данных",
        beforePageText: "Страница"
    }],

    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        },
        {
            dataIndex: 'incomingNumber',
            text: "Вх. №",
            flex: 1
        },
        {
            dataIndex: 'outgoingNumber',
            text: "Исх. №",
            flex: 1
        },
        {
            dataIndex: 'transportationType',
            text: "Тип",
            renderer: TransDocs.service.RenderFactory.transportationTypeRender,
            flex: 1
        },
        {
            dataIndex: 'customerFullName',
            text: "Заказчик",
            flex: 1
        },
        {
            dataIndex: 'carrierFullName',
            text: "Перевозчик",
            flex: 1
        },
        {
            dataIndex: 'managerFullName',
            text: "Менеджер",
            flex: 1
        }
    ]
});