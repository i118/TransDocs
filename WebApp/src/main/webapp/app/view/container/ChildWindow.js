Ext.define("TransDocs.view.container.ChildWindow",{
    extend: "Ext.window.Window",
    draggable: false,
    modal:true,

    initComponent: function(){
        var me = this;
        if(this.parent){
            this.constrainTo = this.parent.getEl();
            this.on('beforeclose', function(){
                me.parent.enable();
            }, this);
            this.on('beforeshow', function(){
                me.parent.disable();
                me.enable();
            }, this);
            this.parent.add(this);
        }
        this.createItems();
        this.callParent(arguments);
    },

    createItems: function(){

    },

    loadRecord: function(record){

    },

    updateRecord: function(){

    },

    getRecord: function(){

    }
});