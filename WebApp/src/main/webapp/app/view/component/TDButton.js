Ext.define("TransDocs.view.component.TDButton",{
    extend: "Ext.Button",
    alias: "widget.tdbutton",
    targetObject: undefined,
    isPermit: false,
    enabled: true,
    showIfDisabled: true,

    initComponent: function(){
        var isPermit =TransDocs.service.PermitActionService.isPermitAction(this.action, this.targetObject);
        this.isPermit = isPermit;
        this.disabled = this.isPermit==false || !this.enabled;
        this.visible = this.disabled && this.showIfDisabled;
        this.callParent(arguments);
    },

    disable: function(silent){
       this.callParent(arguments);
        if(!this.showIfDisabled){
            this.hide();
        }
       this.enabled = false;
    },

    enable: function(silent){
       if(this.isPermit){
           this.callParent(arguments);
           this.enabled=true;
           if(!this.showIfDisabled){
               this.show();
           }
       }else{
           this.enabled =false;
           return;
       }
    },

    reloadPermit: function(){
        this.isPermit = TransDocs.service.PermitActionService.isPermitAction(this.action, this.targetObject);
        if(this.isPermit){
            this.enable();
        }else{
            this.disable();
        }
    }
});