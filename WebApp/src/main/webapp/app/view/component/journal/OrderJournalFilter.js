Ext.define("TransDocs.view.component.journal.OrderJournalFilter", {
    extend: "Ext.toolbar.Toolbar",
    alias: "widget.orderJournalFilter",
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    tbar: [
        {
            text: "Создать",
            handler: "createNewDocument"
        }
    ],

    items: [
        {
            xtype: 'panel',
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            border: false,
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
                            xtype: 'comboSearch',
                            fieldLabel: 'Клиент',
                            queryMode: 'remote',
                            displayField: 'description',
                            valueField: 'objectId',
                            recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
                            queryProperty: "description",
                            searchHandler: "findCustomer",
                            bind: {
                                value: '{restrictions.customer.value}',
                                store: '{customerStore}'
                            }
                        }, {
                            xtype: 'comboSearch',
                            fieldLabel: 'Перевозчик',
                            queryMode: 'remote',
                            displayField: 'description',
                            valueField: 'objectId',
                            recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
                            queryProperty: "description",
                            searchHandler: 'findCarrier',
                            bind: {
                                value: '{restrictions.carrier.value}',
                                store: '{carrierStore}'
                            }
                        }
                    ]

                }, {
                    xtype: 'panel',
                    layout: {
                        type: 'form'
                    },
                    flex: 1.2,
                    border: false,
                    items: [
                        {
                            xtype: 'comboSearch',
                            fieldLabel: 'Менеджер',
                            queryMode: 'remote',
                            displayField: 'description',
                            valueField: 'objectId',
                            recordType: "com.td.model.entity.dictionary.dataset.UserDataSet",
                            queryProperty: "description",
                            searchHandler: 'findManager',
                            bind: {
                                value: '{restrictions.manager.value}',
                                store: '{userStore}'
                            }
                        }, {
                            xtype: 'combobox',
                            fieldLabel: 'Тип',
                            queryMode: 'local',
                            displayField: 'value',
                            valueField: 'key',
                            autoWidth: true,
                            bind: '{restrictions.transportationType.value}',
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
                }
            ]
        }, {
            xtype: 'panel',
            border: false,
            bbar: ['->', {
                text: "Создать",
                handler: "createNewDocument"
            }, {
                text: "Поиск",
                handler: "findDocuments"
            }
            ]
        }
    ]
});