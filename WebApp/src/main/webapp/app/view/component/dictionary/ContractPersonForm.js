Ext.define("TransDocs.view.component.dictionary.ContractPersonForm", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.contractPersonForm",

    defaultType: 'textfield',
    method: 'post',
    layout: 'fit',
    border: false,
    autoHeight: true,
    autoWidth: true,
    autoScroll:false,

    listeners:{
        close: 'cancelContractPerson'
    },

    bbar: ['->',
        {
            xtype: 'button',
            text: 'Отменить',
            action: 'cancelContractPerson',
            handler: 'cancel'
        },
        {
            xtype: 'button',
            text: 'Сохранить',
            action: 'saveContractPerson',
            handler: 'saveContractPerson',
            bind: {
                hidden: '{!isEditMode}'
            }
        }
    ],

    items: [
        {
            xtype: 'fieldset',
            style: "margin-top: 10px; margin-right: 10px;",
            border: false,
            autoHeight: true,
            autoWidth: true,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Имя',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.firstName}'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Фамилия',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.lastName}'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Отчество',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.patronymic}'
                    }
                },
                {
                    xtype: 'combobox',
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.gender}',
                        hideTrigger: '{!isEditMode}'
                    },
                    fieldLabel: 'Пол',
                    labelAlign: 'left',
                    flex: 1,
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'key',
                    store: {
                        xtype: 'store',
                        fields: ['key', 'name'],
                        data: [
                            {
                                key: 'MAN', name: 'Муж'
                            },
                            {
                                key: 'WOMAN', name: 'Жен'
                            }
                        ]
                    }

                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Телефон',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.phone}'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'E-Mail',
                    bind: '{record.email}',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{record.email}'
                    }
                }
            ]
        }

    ]

});