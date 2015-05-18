Ext.define("TransDocs.view.component.file.FileUploadComponent",{
    extend: "Ext.form.Panel",
    alias: "widget.fileUpload",
    width: 400,
    bodyPadding: 10,
    frame: true,
    border: false,
    items: [
        {
            xtype: 'filefield',
            name: 'file',
            fieldLabel: 'Файл',
            labelWidth: 50,
            msgTarget: 'side',
            allowBlank: false,
            anchor: '100%',
            buttonText: 'Выберите файл'
        },{
            xtype:'hiddenfield',
            name: 'containerId'
        },{
            xtype:'hiddenfield',
            name: 'fileType'
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
            handler: 'fileUploadClose'
        },
        {
            xtype: "button",
            text: 'Загрузить',
            action: 'upload',
            handler: 'fileUpload'
        }
    ]
});