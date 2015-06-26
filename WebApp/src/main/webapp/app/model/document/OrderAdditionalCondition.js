/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.model.document.OrderAdditionalCondition', {
    extend: 'TransDocs.model.AbstractModel',

    entityName: "OrderAdditionalCondition",
    fields: [
        {name: "additionalCondition", type:"string"},
        {name: "penalty", type:"string"},
        {name: "agreementContent", type:"string"}
    ],

    getObjectType: function(){
        return "order_additional_condition";
    }
});