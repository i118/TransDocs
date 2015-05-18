Ext.define("TransDocs.view.grid.dictionary.ContractPersonListView", {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contactPersonListView',
    autoWidth: true,
    autoHeight: true,
    frame: true,
    config: {
        contractor: null,
        modelClass: null
    },

    reference: "contactPersonListView",

    bind:{store:'{contractor.persons}'},

    viewConfig : {
        getRowClass : function(record,id){
            if(record.get('deleted') == true){
                return 'hide-row';
            }
        }
    },

    requires: [
        'TransDocs.data.store.dictionary.ContractPersonListViewStore',
        'TransDocs.controller.dictionary.ContractPersonController'
    ],

    isLoaded: false,

    listeners:{
        itemdblclick: "openPerson"
    },

    tbar: [
        {
            xtype: 'button',
            text: 'Добавить',
            action: 'addContractPerson',
            handler: 'addPerson',
            bind: {
                disabled: "{!isEditMode}"
            }
        },
        {
            xtype: 'button',
            text: 'Удалить',
            action: 'deleteContractPerson',
            handler: 'deletePerson',
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
            dataIndex: 'email',
            flex: 1,
            field: {
                xtype: 'textfield',
                emptyText: 'E-Mail'
            }

        }
    ]
});