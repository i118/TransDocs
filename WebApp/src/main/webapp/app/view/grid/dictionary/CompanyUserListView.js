Ext.define("TransDocs.view.grid.dictionary.CompanyUserListView", {
    extend: 'Ext.grid.Panel',
    alias: 'widget.companyUserListView',
    autoWidth: true,
    autoHeight: true,
    frame: true,

    reference: "companyUserListView",

    bind:{store:'{contractor.users}'},

    viewConfig : {
        getRowClass : function(record,id){
            if(record.get('deleted') == true){
                return 'hide-row';
            }
        }
    },


    isLoaded: false,

    listeners:{
        itemdblclick: "openUser"
    },

    tbar: [
        {
            xtype: 'button',
            text: 'Добавить',
            action: 'addUser',
            handler: 'addUser',
            bind: {
                disabled: "{!isEditMode}"
            }
        },
        {
            xtype: 'button',
            text: 'Удалить',
            action: 'deleteUser',
            handler: 'deleteUser',
            bind: {
                disabled: "{!isEditMode}"
            }
        }
    ],

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
        },
        {
            text: 'Пол',
            dataIndex: 'gender',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'Пол'
            },
            renderer: TransDocs.service.RenderFactory.genderRender
        },
        {
            text: 'Телефон',
            dataIndex: 'phone',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'Телефон'
            }
        },
        {
            text: 'E-Mail',
            dataIndex: 'mail',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'E-Mail'
            }

        },
        {
            text: 'Логин',
            dataIndex: 'login',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'Логин'
            }

        }
    ]
});