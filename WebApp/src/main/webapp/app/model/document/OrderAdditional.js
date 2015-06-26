/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.model.document.OrderAdditional', {
    extend: 'Ext.data.Model',
    entityName: "OrderAdditional",
    fields: [
        {name: "transportType", type: "string"},
        {name: "borderCrossing", type: "string"},
        {name: "temperatureRegime", type: "string"},
        {name: "additionalService", type: "string"}
    ]
});
