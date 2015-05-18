Ext.define("TransDocs.view.component.file.RenameFileComponent",{
    extend: "Ext.form.Panel",
    alias: "widget.renameFileComponent",
    width: 400,
    bodyPadding: 10,
    frame: true,
    border: false,
    items: [
        {
            xtype: 'textfield',
            name: 'name',
            fieldLabel: 'Новое имя:',
            labelWidth: 50,
            msgTarget: 'side',
            allowBlank: false,
            anchor: '100%'
        }
    ],

    config: {
        file: undefined
    },

    bbar: [ '->',
        {
            xtype: "button",
            text: "Отмена",
            action: 'close',
            listeners:{
                click:function(button){
                  var wnd = button.up("window");
                  if(wnd){
                      wnd.close();
                  }
                }
            }
        },
        {
            xtype: "button",
            text: 'Сохранить',
            action: 'renameFile',
            listeners:{
                click:function(button){
                    var form = button.up("renameFileComponent");
                    form.updateRecord();
                    var wnd = form.up("window");
                    if(wnd){
                        wnd.close();
                    }
                }
            }
        }
    ],

    updateRecord: function(){
        var newName = this.getForm().getValues()["name"];
        if(this.getFile().get("extension")) {
            newName = newName + "." + this.getFile().get("extension");
        }
        this.getFile().set("name",newName);
    }
})