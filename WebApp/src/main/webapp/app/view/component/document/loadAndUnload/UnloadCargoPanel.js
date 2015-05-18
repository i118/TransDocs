Ext.define("TransDocs.view.component.document.loadAndUnload.UnloadCargoPanel",{
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.unloadCargoPanel",

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
                    fieldLabel: 'Грузополучатель',
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
                    margin: "0 10 0 10",
                    items: [
                        {
                            xtype: 'textfield',
                            fieldLabel: 'Страна разгрузки'
                        }, {
                            xtype: 'textfield',
                            fieldLabel: 'Город',
                            margin: "5 0 5 10"
                        }
                    ]
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Адрес разгрузки'
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
                    xtype: 'datefield',
                    fieldLabel: 'Дата и вр. разгр.'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Таможня назнач.'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Адрес таможни'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Код лицензии'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Способ разгрузки'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Примичание'
                },{
                    xtype: 'textfield',
                    fieldLabel: 'Сотрудник'
                }
            ]
        }
    ]
});