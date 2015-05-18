Ext.define("TransDocs.view.component.document.loadAndUnload.LoadCargoPanel",{
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.loadCargoPanel",

    layout: {
        type: 'vbox',
        align: 'stretch'
    },


    items:[
        {
            xtype: 'fieldset',
            border: false,
            layout: {
                type: 'vbox',
                align: 'stretch'

            },
            margin: "10 0 0 0",
            autoWidth: true,
            defaults: {
                margin: "5 10 5 10",
                flex: 1
            },
            items: [
                {
                    xtype: 'comboSearch',
                    fieldLabel: 'Грузоотправитель',
                    queryMode: 'remote',
                    displayField: 'description',
                    valueField: 'objectId',
                    recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
                    queryProperty: "description",
                    searchHandler: "findCustomer",
                    bind: {
                        value: '{document.customer}',
                        store: '{customerStore}'
                    },
                    listeners: {
                        select: 'selectCustomer',
                        change: 'changeCustomer'
                    }
                },{
                    xtype: "panel",
                    layout: {
                        type: 'hbox',
                        align: 'stretch'

                    },
                    defaults: {
                        flex: 1,
                        autoWidth: true
                    },
                    border:false,
                    items: [
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Страна погрузки'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: 'Город',
                            margin: "0 0 0 10"
                        }
                    ]
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Адрес погрузки'
                },{
                    xtype: "panel",
                    layout: {
                        type: 'hbox',
                        align: 'stretch'

                    },
                    defaults: {
                        flex: 1,
                        autoWidth: true
                    },
                    border:false,
                    items: [
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Отвеств. лицо'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: 'Контактный тел.',
                            margin: "0 0 0 10"
                        }
                    ]
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Способ погрузки'
                },{
                    xtype: 'textfield',
                    margin: "0 10 5 10",
                    fieldLabel: 'Таможня отправл.'
                },{
                    xtype: 'textfield',
                    margin: "0 10 5 10",
                    fieldLabel: 'Примичание'
                }
            ]
        }
    ]
});