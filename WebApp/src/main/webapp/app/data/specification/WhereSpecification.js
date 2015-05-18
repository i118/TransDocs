Ext.define("TransDocs.data.specification.WhereSpecification", {
    extend: "Ext.Base",

    requires: [
        "TransDocs.data.specification.PredicateSpecification"
    ],

    restriction: null,
    predicate: null,

    constructor: function () {
        this.callParent(arguments);
    },

    addPredicate: function (operator){
        this.predicate = Ext.create("TransDocs.data.specification.PredicateSpecification");
        return this.predicate.predicate(operator);
    },

    addRestriction: function(restriction){
        this.restriction = restriction;
        return this;
    }
})