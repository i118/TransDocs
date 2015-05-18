Ext.define("TransDocs.data.specification.Specification",{
    extend: "Ext.Base",

    requires: [
        "TransDocs.data.specification.WhereSpecification"
    ],
    recordType: null,
    where: null,
    order: null,
    max: 0,
    offset: 0,

    constructor: function(){
        this.callParent(arguments);
    },

    addWhere: function(){
        this.where= Ext.create("TransDocs.data.specification.WhereSpecification");
        return this.where;
    },

    addOrder: function(order){
        this.order = order;
        return this;
    },

    from: function(recordType){
        this.recordType = recordType;
        return this;
    },

    setMax: function(max){
        this.max = max;
        return this;
    },

    setOffset: function(offset){
        this.offset = offset;
        return this;
    }
})