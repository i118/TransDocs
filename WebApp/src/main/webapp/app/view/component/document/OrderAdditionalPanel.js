Ext.define("TransDocs.view.component.document.OrderAdditionalPanel", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: 'widget.orderAdditionalPanel',
    title: 'Дополнительно',

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    items: [{
        xtype: 'fieldset',
        title: 'Дополнительно',
        layout: {
            type: 'hbox',
            align: 'stretch'

        },
        autoWidth: true,
        defaults: {
            style: "margin-left: 10px; margin-right: 10px;",
            flex: 1
        },
        items: [{
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
                xtype: 'comboSearch',
                fieldLabel: 'Тип Т/С',
                queryMode: 'remote',
                displayField: 'description',
                valueField: 'description',
                queryProperty: "description",
                autoWidth: true,
                recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
                searchHandler: 'findTransportType',
                globalRestrictions: [
                    {
                        propertyName: "dictionaryType",
                        value: "TRANSPORT_TYPE",
                        operator: "="
                    }
                ],
                bind: {
                    value: '{document.transportType}',
                    store: '{transportTypeStore}'
                }
            }, {
                xtype: 'combobox',
                fieldLabel: 'Погранпереход',
                queryMode: 'local',
                displayField: 'name',
                valueField: 'key',
                store: {
                    xtype: 'store',
                    fields: ['key', 'name'],
                    data: []
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
                    xtype: 'textfield',
                    fieldLabel: 'Темп. режим'
                }, {
                    xtype: 'combobox',
                    fieldLabel: 'Доп. услуги',
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'key',
                    store: {
                        xtype: 'store',
                        fields: ['key', 'name'],
                        data: []
                    }
                }
                ]
            }

        ]
    }, {
        xtype: 'fieldset',
        title: 'Выделенный подвижный состав и водитель',
        layout: {
            type: 'hbox',
            align: 'stretch'

        },
        autoWidth: true,
        defaults: {
            style: "margin-left: 10px; margin-right: 10px;",
            flex: 1
        },
        items: [{
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
                    xtype: 'textfield',
                    fieldLabel: 'Тягач'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Прицепы'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. выд.'
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
                    fieldLabel: 'Водитель',
                    queryMode: 'local',
                    displayField: 'name',
                    valueField: 'key',
                    store: {
                        xtype: 'store',
                        fields: ['key', 'name'],
                        data: []
                    }
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. номер'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Моб. тел.'
                }
                ]
            }

        ]
    }, {
        xtype: 'fieldset',
        title: 'Дополнительные условия',
        layout: {
            type: 'hbox',
            align:'stretch'

        },
        flex: 5,
        autoWidth: true,
        defaults: {
            style: "margin-left: 10px; margin-right: 10px;",
            flex: 1
        },
        items: [
            {
                xtype: 'tabpanel',
                border:true,
                layout: 'fit',
                items:[
                    {
                        xtype: 'panel',
                        title: "Для перевозчика",
                        layout: {
                            type: 'vbox',
                            align: 'stretch'
                        },
                        defaults: {
                            style: "margin-left: 10px; margin-right: 10px;"
                        },
                        border: false,
                        items: [
                            {
                                xtype: 'textarea',
                                fieldLabel: 'Дополнительные условия',
                                style: "margin-left: 10px; margin-right: 10px; margin-top: 10px;"
                            }, {
                                xtype: 'combobox',
                                fieldLabel: 'Штрафные санкции',
                                queryMode: 'local',
                                displayField: 'name',
                                valueField: 'key',
                                store: {
                                    xtype: 'store',
                                    fields: ['key', 'name'],
                                    data: []
                                }
                            }, {
                                xtype: 'textarea',
                                fieldLabel: 'Текст договора с перевозчиком',
                                labelAlign: 'top',
                                autoScroll: true,
                                flex:3
                            }
                        ]
                    },
                    {
                        xtype: 'panel',
                        title: "Для заказчика",
                        layout: {
                            type: 'vbox',
                            align: 'stretch'
                        },
                        defaults: {
                            style: "margin-left: 10px; margin-right: 10px; "

                        },
                        border: false,
                        items: [
                            {
                                xtype: 'textarea',
                                fieldLabel: 'Дополнительные условия',
                                style: "margin-left: 10px; margin-right: 10px; margin-top: 10px;"
                            }, {
                                xtype: 'combobox',
                                fieldLabel: 'Штрафные санкции',
                                queryMode: 'local',
                                displayField: 'name',
                                valueField: 'key',
                                store: {
                                    xtype: 'store',
                                    fields: ['key', 'name'],
                                    data: []
                                }
                            }, {
                                xtype: 'textarea',
                                fieldLabel: 'Текст договора с заказчиком',
                                labelAlign: 'top',
                                autoScroll: true,
                                flex:3
                            }
                        ]
                    }
                ]
            }
        ]
    }
    ]
});