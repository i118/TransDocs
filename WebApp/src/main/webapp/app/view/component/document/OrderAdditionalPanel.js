Ext.define("TransDocs.view.component.document.OrderAdditionalPanel", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: 'widget.orderAdditionalPanel',
    title: 'Дополнительно',

    requires: [
        "TransDocs.viewmodel.document.OrderAdditionalViewModel",
        "TransDocs.controller.document.OrderAdditionalController"
    ],

    controller: "orderadditional",

    viewModel: {
        type: "orderAdditional"
    },

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
                    value: '{orderAdditional.transportType}',
                    store: '{simpleDictionaryStore}'
                }
            }, {
                xtype: 'comboSearch',
                fieldLabel: 'Погранпереход',
                queryMode: 'remote',
                displayField: 'description',
                valueField: 'description',
                queryProperty: "description",
                autoWidth: true,
                recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
                searchHandler: 'findBorderCrossing',
                globalRestrictions: [
                    {
                        propertyName: "dictionaryType",
                        value: "BORDER_CROSSING",
                        operator: "="
                    }
                ],
                bind: {
                    value: '{orderAdditional.borderCrossing}',
                    store: '{simpleDictionaryStore}'
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
                    fieldLabel: 'Темп. режим',
                    bind: '{orderAdditional.temperatureRegime}'
                }, {
                    xtype: 'comboSearch',
                    fieldLabel: 'Доп. услуги',
                    queryMode: 'remote',
                    displayField: 'description',
                    valueField: 'description',
                    queryProperty: "description",
                    autoWidth: true,
                    recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
                    searchHandler: 'findAdditionalService',
                    globalRestrictions: [
                        {
                            propertyName: "dictionaryType",
                            value: "ADDITIONAL_SERVICE",
                            operator: "="
                        }
                    ],
                    bind: {
                        value: '{orderAdditional.additionalService}',
                        store: '{simpleDictionaryStore}'
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
                    fieldLabel: 'Тягач',
                    bind: '{orderTransport.car.carBrand}'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Прицепы',
                    bind: '{orderTransport.trailer}'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. выд.',
                    bind: '{orderTransport.driverPassport.issuedPassport}'
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
                    queryMode: 'remote',
                    displayField: 'description',
                    valueField: 'objectId',
                    bind: {
                        value: '{driver}',
                        store: '{transportDrivers}',
                        disabled: '{!isSelectedCarrier}'
                    }
                    //triggers:{
                    //    info: {
                    //        type: 'personInfoTrigger',
                    //        handler: 'viewPerson'
                    //    }
                    //}
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. номер',
                    bind: '{orderTransport.driverPassport.number}'
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