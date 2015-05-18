Ext.define('TransDocs.view.grid.dictionary.DriverListView', {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    alias: 'widget.driverListView',

    config: {
        contractor: null,
        modelClass: null
    },

    bind: {store:'{contractor.drivers}'},

    viewConfig : {
        getRowClass : function(record,id){
            if(record.get('deleted') == true){
                return 'hide-row';
            }
        }
    },

    isLoaded: false,

    tbar:[
        {
            xtype: 'button',
            text: 'Добавить',
            action: 'addDriver',
            handler: 'addDriver',
            bind: {
                disabled: "{!isEditMode}"
            }
        },
        {
            xtype: 'button',
            text: 'Удалить',
            action: 'deleteDriver',
            handler: 'deleteDriver',
            bind: {
                disabled: "{!isEditMode}"
            }
        }
    ],

    listeners:{
      itemdblclick: "openDriver"
    },


    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        }, {
            text: 'Имя',
            dataIndex: 'firstName',
            field: {
                xtype: 'textfield',
                emptyText: 'Имя'
            }
        },
        {
            text: 'Фамилия',
            dataIndex: 'lastName',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'Фамилия'
            }
        },
        {
            text: 'Отчество',
            dataIndex: 'patronymic',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'Отчество'
            }
        }
    ]
});