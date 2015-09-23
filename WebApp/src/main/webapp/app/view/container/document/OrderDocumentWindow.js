Ext.define("TransDocs.view.container.document.OrderDocumentWindow",{
    extend: "TransDocs.view.container.Window",

    autoMask: true,

    applyMask: function(){
      if(this.spinner){
            this.spinner.applyMask();
      }
    },

    hideMask: function(){
        if(this.spinner){
            this.spinner.hideMask();
        }
    }
})