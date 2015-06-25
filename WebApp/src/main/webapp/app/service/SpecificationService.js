Ext.define("TransDocs.service.SpecificationService", {
    extend: 'Ext.Class',
    singleton: true,

    requires: [
        "TransDocs.data.specification.Specification"
    ],

    constructor: function(){
        this.callParent(arguments);
    },

    from: function(recordType){
        return Ext.create("TransDocs.data.specification.Specification").from(recordType);
    },

    specification: function(store, operation, viewModel){
        var specification = this.from(viewModel.get("recordType"));
        var restrictions = viewModel.get("restrictions");
        var where;
        var buildRestriction= function(restrictionName, viewModel){
            var operator = viewModel.get("restrictions."+restrictionName+".operator");
            var value = viewModel.get("restrictions."+restrictionName+".value");
            if(value && value.isModel){
                value = value.getId();
            }
            if(operator==="LIKE"){
                value = "%"+value+"%";
            }
            restriction = {
                propertyName: viewModel.get("restrictions."+restrictionName+".propertyName"),
                value: value,
                operator: operator
            }
            return restriction;
        }

        var buildOrder = function(viewModel){
            var order = {
                fields: viewModel.get("order.fields"),
                orderType: viewModel.get("order.orderType")
            }
            return order;
        }
        if (restrictions) {
            for (var restrictionName in restrictions) {
                var value = viewModel.get("restrictions." + [restrictionName] + ".value");
                if (value!=null && value !== "" && value!=undefined) {
                    if (!where) {
                        where = specification.addWhere().addRestriction(buildRestriction(restrictionName, viewModel));
                    } else {
                        where = where.addPredicate("AND").addRestriction(buildRestriction(restrictionName, viewModel));
                    }
                }
            }
        }
        if(operation.getSorters() && operation.getSorters().length>0){
            var fields = [];
            var orderType;
            for(var i in operation.getSorters()){
                var sorter = operation.getSorters()[i];
                fields.push(sorter.getProperty());
                orderType = sorter.getDirection();
            }
            viewModel.set("order.fields", fields);
            viewModel.set("order.orderType", orderType ? orderType : "desc");
        }
        if(viewModel.get("order")){
            specification.addOrder(buildOrder(viewModel));
        }
        if(operation.getLimit()>0){
            specification.setMax(operation.getLimit());
            if(operation.getStart()>0){
                specification.setOffset(operation.getStart());
            }else{
                specification.setOffset(0);
            }
        }
        return specification;
    }
});