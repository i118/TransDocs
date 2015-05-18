Ext.define("TransDocs.data.specification.PredicateSpecification", {
    extend: "Ext.Base",

    where: null,
    operator: null,

    constructor: function () {
        this.callParent(arguments);
    },

    predicate: function (predicate){
        this.operator = predicate;
        this.where =  Ext.create("TransDocs.data.specification.WhereSpecification");
        return this.where;
    }
})