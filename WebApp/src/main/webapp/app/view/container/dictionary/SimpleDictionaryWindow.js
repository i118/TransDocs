Ext.define("TransDocs.view.container.dictionary.SimpleDictionaryWindow", {
    extend: "TransDocs.view.container.ChildWindow",

    bind: {
        title: '{dictionary.description}'
    },

    width: 310,
    autoHeight: true,

    items:[
        {
            xtype: "abstractForm",
            layout: 'anchor',
            border: false,
            bbar: ['->',
                {
                    xtype: 'button',
                    text: 'Отменить',
                    action: 'cancelDictionary',
                    handler: 'cancelDictionary'
                },
                {
                    xtype: 'button',
                    text: 'Сохранить',
                    action: 'saveDictionary',
                    handler: 'saveDictionary',
                    bind: {
                        hidden: '{!isEditMode}'
                    }
                }
            ],

            defaults: {
                cls: 'field-margin'
            },
            items:[
                {
                    xtype: 'textfield',
                    fieldLabel: 'Наименование',
                    labelAlign: 'left',
                    flex: 1,
                    bind:{
                        editable: '{isEditMode}',
                        value: '{dictionary.description}'
                    }
                }
            ]
        }
    ]
});