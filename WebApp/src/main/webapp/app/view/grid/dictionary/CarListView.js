
Ext.define('TransDocs.view.grid.dictionary.CarListView', {
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    alias: 'widget.carListView',

    config: {
        contractor: null,
        modelClass: null
    },

    bind: {store:'{contractor.cars}'},

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
            action: 'addCar',
            handler: 'addCar',
            bind: {
                disabled: "{!isEditMode}"
            }
        },
        {
            xtype: 'button',
            text: 'Удалить',
            action: 'deleteCar',
            handler: 'deleteCar',
            bind: {
                disabled: "{!isEditMode}"
            }
        }
    ],

    listeners:{
        itemdblclick: "openCar"
    },

    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        }, {
            text: 'Марка машины',
            dataIndex: 'carBrand',
            flex: 1
        },
        {
            text: 'Гос. номер машины',
            dataIndex: 'carNumber',
            flex: 1
        },
        {
            text: 'Марка прицепа',
            dataIndex: 'trailerBrand',
            flex: 1
        },
        {
            text: 'Гос. номер прицепа',
            dataIndex: 'trailerNumber',
            flex: 1
        },
        {
            text: 'Тип прицепа',
            dataIndex: 'trailerType',
            flex: 1
        },
        {
            text: 'Кубатура',
            dataIndex: 'cubage',
            flex: 1
        }
    ]
});