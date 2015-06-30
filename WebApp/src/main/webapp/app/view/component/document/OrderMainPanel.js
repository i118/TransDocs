Ext.define("TransDocs.view.component.document.OrderMainPanel", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: 'widget.orderMainPanel',
    title: 'Заявка',

    requires: [
        "TransDocs.data.store.dictionary.CustomerStore"
    ],
    layout: {
        type: 'vbox',
        align: 'stretch'
    },



    items: [{
        xtype: 'fieldset',
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        title: 'Сделка',
        defaults: {
            style: "margin-left: 15px; margin-right: 15px;",
            flex: 1,
            autoWidth: true
        },
        style: "margin-top: 5px;",
        items: [
            {
                xtype: 'panel',
                border: false,
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                flex: 1,
                defaults: {
                    style: "margin-bottom:5px",
                    flex: 1,
                    autoWidth: true
                },
                items: [
                    {
                        xtype: 'panel',
                        border: false,
                        layout: {
                            type: 'hbox',
                            align: 'stretch'
                        },
                        defaults:{
                            autoWidth: true
                        },
                        items: [
                            {
                                xtype: 'displayfield',
                                style: "margin-right: 10px;",
                                labelWidth: 50,
                                bind: '{document.outgoingNumber.number}',
                                fieldLabel: 'Исх. №'
                            }, {
                                xtype: 'displayfield',
                                labelWidth: 25,
                                bind: '{document.outgoingNumber.numberDate}',
                                renderer: Ext.util.Format.dateRenderer('d.m.Y'),
                                fieldLabel: 'от'
                            }
                        ]
                    }, {
                        xtype: 'panel',
                        border: false,
                        layout: {
                            type: 'hbox',
                            align: 'stretch'
                        },
                        defaults:{
                            autoWidth: true
                        },
                        items: [
                            {
                                xtype: 'displayfield',
                                style: "margin-right: 10px;",
                                bind: '{document.incomingNumber.number}',
                                labelWidth: 50,
                                editable:false,
                                fieldLabel: 'Вх. №'
                            }, {
                                xtype: 'displayfield',
                                labelWidth: 25,
                                bind: '{document.incomingNumber.numberDate}',
                                renderer: Ext.util.Format.dateRenderer('d.m.Y'),
                                editable:false,
                                fieldLabel: 'от'
                            }
                        ]
                    },{
                        xtype: 'panel',
                        border:false,
                        layout: {
                            type: 'hbox',
                            align: 'stretch'
                        },
                        defaults: {
                            flex: 1,
                            autoWidth: true
                        },
                        items: [
                            //{
                            //    xtype: 'combobox',
                            //    fieldLabel: 'Владелец сделки',
                            //    queryMode: 'remote',
                            //    displayField: 'description',
                            //    valueField: 'objectId',
                            //    editable: false,
                            //    hideTrigger: true,
                            //    autoWidth: true,
                            //    bind: {
                            //        value: '{document.company}',
                            //        store: '{companyStore}'
                            //    }
                            //}
                            {
                                xtype: "textfield",
                                fieldLabel: 'Владелец сделки',
                                editable: false,
                                bind: '{document.company.description}'
                            }
                        ]
                    }
                ]
            }, {
                xtype: 'panel',
                border: false,
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
                flex: 1,
                defaults: {
                    style: "margin-bottom:5px",
                    flex: 1,
                    autoWidth: true
                },
                items: [{
                    xtype: 'panel',
                    border:false,
                    layout: {
                        type: 'hbox',
                        align: 'stretch'
                    },
                    defaults: {
                        flex: 1,
                        autoWidth: true
                    },
                    items: [
                        {
                            xtype: 'comboSearch',
                            fieldLabel: 'Заголовок',
                            queryMode: 'remote',
                            displayField: 'description',
                            valueField: 'description',
                            queryProperty: "description",
                            autoWidth: true,
                            recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
                            searchHandler: 'findOrderTitle',
                            globalRestrictions: [
                                {
                                    propertyName: "dictionaryType",
                                    value: "ORDER_TITLE",
                                    operator: "="
                                }
                            ],
                            bind: {
                                value: '{document.title}',
                                store: '{orderTitleStore}'
                            }
                        }
                    ]
                },
                    {
                        xtype: 'panel',
                        border: false,
                        layout: {
                            type: 'hbox',
                            align: 'stretch'
                        },
                        defaults:{
                            flex: 1,
                            autoWidth: true
                        },
                        items: [
                            {
                                xtype: 'combobox',
                                fieldLabel: 'Тип',
                                queryMode: 'local',
                                displayField: 'value',
                                valueField: 'key',
                                autoWidth: true,
                                bind: '{document.transportationType}',
                                store: {
                                    xtype: 'store',
                                    fields: ['key', 'value'],
                                    data: [
                                        {key: 'INTERCITY', value: 'Междугородняя'},
                                        {key: 'CITY', value: 'Городская'},
                                        {key: 'SUBURBAN', value: 'Пригородная'},
                                        {key: 'INTERNATIONAL', value: 'Международная'},
                                        {key: 'PASSENGER', value: 'Пассажирская'}
                                    ]
                                }
                            }
                        ]
                    },{
                        xtype: 'panel',
                        border: false,
                        layout: {
                            type: 'hbox',
                            align: 'stretch'
                        },
                        defaults:{
                            flex: 1,
                            autoWidth: true
                        },
                        items: [
                            {
                                xtype: 'comboSearch',
                                fieldLabel: 'Ответ. менеджер',
                                queryMode: 'remote',
                                displayField: 'description',
                                valueField: 'objectId',
                                recordType: "com.td.model.entity.dictionary.dataset.UserDataSet",
                                bind: {
                                    value: '{document.manager}',
                                    store: '{userStore}'
                                },
                                queryProperty: "description",
                                searchHandler: 'findManager',
                                listeners: {
                                    change: 'changeManager'
                                }
                            }
                        ]
                    },
                ]
            }
        ]
    }, {
        xtype: 'fieldset',
        title: 'Заказчик',
        layout: {
            type: 'hbox',
            align: 'stretch'

        },
        autoWidth: true,
        defaults: {
            style: "margin-left: 10px; margin-right: 10px;",
            flex: 1
        },
        items: [
            {
                xtype: 'panel',
                layout: {
                    type: 'form',
                    align: 'stretch'
                },
                defaults: {
                    flex: 1,
                    autoWidth: true
                },
                border: false,
                items: [
                    {
                        xtype: 'comboSearch',
                        fieldLabel: 'Наименование',
                        queryMode: 'remote',
                        displayField: 'description',
                        valueField: 'objectId',
                        recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
                        queryProperty: "description",
                        searchHandler: "findCustomer",
                        bind: {
                            value: '{customer}',
                            store: '{customerStore}'
                        },
                        listeners: {
                            change: 'changeCustomer'
                        }
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Тел./Факс',
                        bind: '{document.customerPhone}'

                    }, {
                        xtype: 'combobox',
                        bind: '{document.customerPaymentMethod}',
                        fieldLabel: 'Форма оплаты',
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
                                    key: 'CASHLESS', name: 'Безналичный расчет'
                                },
                                {
                                    key: 'SPOTCASH', name: 'Наличный расчет'
                                }
                            ]
                        }

                    }
                ]
            },
            {
                xtype: 'panel',
                layout: {
                    type: 'form',
                    align: 'stretch'
                },
                defaults: {
                    flex: 1,
                    autoWidth: true
                },
                border: false,
                items: [{
                    xtype: 'combobox',
                    fieldLabel: 'Контактное лицо',
                    queryMode: 'remote',
                    displayField: 'description',
                    valueField: 'objectId',
                    autoLoadOnValue:true,
                    bind: {
                        value: '{document.customerPersonId}',
                        store: '{customerPersons}',
                        disabled: '{!isSelectedCustomer}'
                    },
                    listeners: {
                        select: 'selectCustomerPerson'
                    },
                    triggers:{
                        info: {
                            type: 'objectInfoTrigger',
                            handler: 'viewPerson'
                        }
                    }

                },{
                    xtype: 'textfield',
                    fieldLabel: 'Адрес',
                    bind: '{document.customerAddress}',
                    autoWidth: true,
                    columnWidth: 0.50
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'E-Mail',
                    bind: '{document.customerEmail}'
                }
                ]
            }

        ]
    }, {
        xtype: 'fieldset',
        title: 'Общие итоговые суммы',
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        items: [
            {
                xtype: 'panel',
                layout: {
                    type: 'form'
                },
                flex: 1.2,
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        fieldLabel: 'От заказчика(Руб.)',
                        editable: false,
                        anchor: '50%'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Перевозчикам(Руб.)',
                        editable: false,
                        anchor: '50%'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Тек.комис.(Руб.)',
                        editable: false,
                        anchor: '50%'
                    }
                ]
            }, {
                xtype: 'panel',
                layout: {
                    type: 'form'
                },
                defaults: {
                    autoWidth: true
                },
                flex: 1.1,
                border: false,
                items: [
                    {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Оплачено(Руб.)'
                    }, {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Оплачено(Руб.)'
                    }, {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Ож. Комис.(Руб.)'
                    }
                ]
            }, {
                xtype: 'panel',
                layout: {
                    type: 'form',
                    align: 'stretch'
                },
                defaults: {
                    autoWidth: true
                },
                flex: 1,
                border: false,
                items: [
                    , {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Долг(Руб.)'
                    }, {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Долг(Руб.)'
                    }, {
                        xtype: 'textfield',
                        editable: false,
                        fieldLabel: 'Дистанция'
                    }
                ]
            }
        ]
    }, {
        xtype: 'fieldset',
        title: 'Генеральный перевозчик',
        layout: {
            type: 'hbox',
            align: 'stretch'
        },
        defaults: {
            style: "margin-left: 10px; margin-right: 10px;",
            flex: 1
        },
        items: [
            {
                xtype: 'panel',
                layout: {
                    type: 'form',
                    align: 'stretch'
                },
                defaults: {
                    flex: 1,
                    autoWidth: true
                },
                border: false,
                items: [
                    {
                        xtype: 'comboSearch',
                        fieldLabel: 'Наименование',
                        queryMode: 'remote',
                        displayField: 'description',
                        valueField: 'objectId',
                        recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
                        queryProperty: "description",
                        searchHandler: 'findCarrier',
                        bind: {
                            value: '{carrier}',
                            store: '{carrierStore}'
                        },
                        listeners: {
                            change: 'changeCarrier'
                        }
                    }, {
                        xtype: 'combobox',
                        fieldLabel: 'Контактное лицо',
                        queryMode: 'remote',
                        displayField: 'description',
                        valueField: 'objectId',
                        bind: {
                            value: '{document.carrierPersonId}',
                            store: '{carrierPersons}',
                            disabled: '{!isSelectedCarrier}'
                        },
                        listeners: {
                            select: 'selectCarrierPerson'
                        },
                        triggers:{
                            info: {
                                type: 'objectInfoTrigger',
                                handler: 'viewPerson'
                            }
                        }
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Адрес',
                        bind: '{document.carrierAddress}'
                    }, {
                        xtype: 'textfield',
                        fieldLabel: 'Тел./Факс',
                        bind: '{document.carrierPhone}'
                    }, {
                        xtype: 'combobox',
                        bind: '{document.carrierPaymentMethod}',
                        fieldLabel: 'Форма оплаты',
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
                                    key: 'CASHLESS', name: 'Безналичный расчет'
                                },
                                {
                                    key: 'SPOTCASH', name: 'Наличный расчет'
                                }
                            ]
                        }

                    }
                ]
            },
            {
                xtype: 'panel',
                layout: {
                    type: 'form',
                    align: 'stretch'
                },
                defaults: {
                    flex: 1,
                    autoWidth: true
                },
                border: false,
                items: [
                    {
                        xtype: 'combobox',
                        fieldLabel: 'Договор',
                        queryMode: 'local',
                        displayField: 'name',
                        valueField: 'key',
                        store: {
                            xtype: 'store',
                            fields: ['key', 'name'],
                            data: []
                        }
                    }, {
                        xtype: 'comboSearch',
                        fieldLabel: 'Срок оплаты',
                        queryMode: 'remote',
                        displayField: 'description',
                        valueField: 'description',
                        queryProperty: "description",
                        autoWidth: true,
                        recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
                        searchHandler: 'findPaymentDate',
                        globalRestrictions: [
                            {
                                propertyName: "dictionaryType",
                                value: "PAYMENT_DATE",
                                operator: "="
                            }
                        ],
                        bind: {
                            value: '{document.paymentDate}',
                            store: '{paymentDateStore}'
                        }
                    },{
                        xtype: 'textfield',
                        fieldLabel: 'Стоимость(пр.)'
                    },{
                        xtype: 'textfield',
                        fieldLabel: 'Стоимость'
                    }
                ]
            }

        ]
    }
    ]
});
