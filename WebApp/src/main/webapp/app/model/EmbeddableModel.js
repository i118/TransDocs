Ext.define("TransDocs.model.EmbeddableModel", {
    extend: "Ext.data.Model",

    constructor: function () {
        this.callParent(arguments);
        this.phantom = false;
    },

    isDirty: function(){
        var fields = this.getFields();
        for(var i = 0; i < fields.length; ++i){
            var field = fields[i];
            if(this.isModified(field.getName())){
                return true;
            }
        }
        return false;
    }
});