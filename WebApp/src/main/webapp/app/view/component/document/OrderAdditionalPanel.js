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
                    xtype: 'combobox',
                    fieldLabel: 'Тягач',
                    queryMode: 'remote',
                    displayField: 'description',
                    valueField: 'objectId',
                    reference: "carComboBox",
                    bind: {
                        value: '{document.orderTransport.carId}',
                        store: '{document.carrier.cars}',
                        disabled: '{!isSelectedCarrier}'
                    },
                    listeners: {
                        select: "selectCar"
                    },
                    triggers:{
                        info: {
                            type: 'objectInfoTrigger',
                            handler: 'viewCar'
                        }
                    }
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Прицепы',
                    bind: '{document.orderTransport.trailer}'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. выд.',
                    bind: '{document.orderTransport.driverPassport.issuedPassport}'
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
                        value: '{document.orderTransport.driverId}',
                        store: '{document.carrier.drivers}',
                        disabled: '{!isSelectedCarrier}'
                    },
                    listeners: {
                        select: "selectDriver"
                    },
                    triggers:{
                        info: {
                            type: 'objectInfoTrigger',
                            handler: 'viewDriver'
                        }
                    }
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Пасп. номер',
                    bind: '{document.orderTransport.driverPassport.number}'
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Моб. тел.',
                    bind: '{document.orderTransport.driverPhone}'
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
                                style: "margin-left: 10px; margin-right: 10px; margin-top: 10px;",
                                bind: '{document.carrierAdditionalCondition.additionalCondition}'
                            }, {
                                xtype: 'textfield',
                                fieldLabel: 'Штрафные санкции',
                                bind: '{document.carrierAdditionalCondition.penalty}'
                            }, {
                                xtype: 'textarea',
                                fieldLabel: 'Текст договора с перевозчиком',
                                labelAlign: 'top',
                                autoScroll: true,
                                bind: '{document.carrierAdditionalCondition.agreementContent}',
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
                                style: "margin-left: 10px; margin-right: 10px; margin-top: 10px;",
                                bind: '{document.customerAdditionalCondition.additionalCondition}'
                            }, {
                                xtype: 'textfield',
                                fieldLabel: 'Штрафные санкции',
                                bind: '{document.customerAdditionalCondition.penalty}'
                            }, {
                                xtype: 'textarea',
                                fieldLabel: 'Текст договора с заказчиком',
                                bind: '{document.customerAdditionalCondition.agreementContent}',
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