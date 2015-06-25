Ext.define("TransDocs.model.tree.SimpleTreeModel",{
    extend: 'TransDocs.model.tree.TreeBase',

    fields: [
        {name: 'description', type: 'string'},
        {name: 'leaf', type:"boolean", convert: function(value, record){
            return !record.isFirst();
        }}
    ],

    getObjectType: function(){
        return "simple_tree";
    }
});