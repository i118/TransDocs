Ext.define("TransDocs.view.component.file.FolderComponent",{
    extend: "Ext.form.Panel",
    alias: "widget.folderComponent",
    width: 400,
    bodyPadding: 10,
    frame: true,
    border: false,
    items: [
        {
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Имя папки',
            labelWidth: 50,
            msgTarget: 'side',
            allowBlank: false,
            anchor: '100%'
        }
    ],

    config: {
        containerModel: undefined,
        modelClass: null
    },

    bbar: [ '->',
        {
            xtype: "button",
            text: "Отмена",
            action: 'close',
            handler: 'folderClose'
        },
        {
            xtype: "button",
            text: 'Сохранить',
            action: 'saveFolder',
            handler: 'saveFolder'
        }
    ]
});