Ext.define("TransDocs.view.component.dictionary.DriverForm", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.driverForm",
    layout: {
        type: 'table',
        columns: 2
    },
    autoScroll: true,
    defaultType: 'textfield',
    method: 'post',
    overflowY: 'auto',
    config: {
        modelClass: null,
        contractor: null
    },

    listeners: {
        close: 'closeDriverForm'
    },

    bbar: [
        '->', {
            xtype: 'button',
            text: 'Отменить',
            action: 'CloseDriverForm',
            handler: 'cancel'
        },
        {
            xtype: 'button',
            text: 'Сохранить',
            action: 'SaveDriver',
            handler: 'saveDriver',
            bind: {
                hidden: "{!isEditMode}"
            }
        }
    ],

    items: [
        {
            xtype: 'fieldset',
            title: 'Личные данные',
            height: 135,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Имя',
                    labelAlign: 'left',
                    flex: 1,
                    bind: {
                        editable: '{isEditMode}',
                        value: '{record.firstName}'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Фамилия',
                    labelAlign: 'left',
                    flex: 1,
                    bind: {
                        editable: '{isEditMode}',
                        value: '{record.lastName}'
                    }
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Отчество',
                    labelAlign: 'left',
                    flex: 1,
                    bind: {
                        editable: '{isEditMode}',
                        value: '{record.patronymic}'
                    }
                },
                {
                    xtype: 'combobox',
                    bind: {
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

                }
            ]
        },
        {
            xtype: 'fieldset',
            title: 'Паспортрные данные',
            height: 135,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Серия паспорта',
                    bind: {
                        value: '{record.passport.serial}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Номер паспорта',
                    bind: {
                        value: '{record.passport.number}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Кем выдан',
                    bind: {
                        value: '{record.passport.issuedPassport}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                }
                ,
                {
                    xtype: 'textfield',
                    fieldLabel: 'Адрес регистрации',
                    bind: {
                        value: '{record.registrationAddress}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                }

            ]
        }
        ,
        {
            xtype: 'fieldset',
            title: 'Прочее',
            height: 135,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Телефон',
                    bind: {
                        value: '{record.phone}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Водительское удостоверение',
                    bind: {
                        value:'{record.drivingLicense}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'combobox',
                    name: 'car',
                    bind: {
                        value: '{record.defaultCarId}',
                        store: '{contractor.cars}',
                        editable: '{isEditMode}',
                        hideTrigger: '{!isEditMode}'
                    },
                    fieldLabel: 'Машина',
                    labelAlign: 'left',
                    flex: 1,
                    queryMode: 'local',
                    displayField: 'description',
                    valueField: 'objectId',
                    listeners: {
                        select: 'selectCar'
                    }
                }

            ]
        }
    ]

});