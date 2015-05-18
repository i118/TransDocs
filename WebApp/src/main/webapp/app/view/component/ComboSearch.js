Ext.define("TransDocs.view.component.ComboSearch",{
    extend: "Ext.form.field.ComboBox",
    alias: "widget.comboSearch",

    requires:[
        "TransDocs.service.SpecificationService"
    ],

    defaultListenerScope: true,
    config: {
        searchHandler: null,
        recordType: null,
        queryProperty: null,
        queryOperator: 'LIKE'
    },

    listeners: {
        beforequery: "onBeforeQuery"
    },

    globalRestrictions: [],



    constructor: function(config){
        var triggers = {
            triggers:{
                search: {
                    type: 'searchTrigger',
                    handler: config.searchHandler
                }
            }
        };
        config = Ext.apply(config, triggers);
        this.callParent(arguments);
    },

    onBeforeQuery: function(queryPlan, eOpts ){
        if(!this.getRecordType()) throw "record type can no be null";
        var spec =  TransDocs.service.SpecificationService.from(this.getRecordType());
        var where =spec.addWhere();
        if(queryPlan.query){
            var me = this;
            if(!this.getQueryProperty()) throw "query property can not be null";
            var value = this.getQueryOperator() === "LIKE" ? queryPlan.query+"%" : queryPlan.query;
            var restriction = {
                propertyName: me.getQueryProperty(),
                value: value,
                operator: me.getQueryOperator()
            }
            where = spec.addWhere().addRestriction(restriction).addPredicate("AND");
        }
        where.addRestriction({
            propertyName: "deleted",
            value: false,
            operator: "="
        });

        if(this.globalRestrictions)
        for(var index in this.globalRestrictions){
            var restriction = this.globalRestrictions[index];
            where = where.addPredicate("AND");
           where.addRestriction(restriction);
        }
        var store = this.getStore();
        store.setExtraParams({specification: spec});
    }

});