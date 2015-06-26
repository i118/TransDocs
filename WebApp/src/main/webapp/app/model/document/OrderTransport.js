/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.model.document.OrderTransport', {
    extend: 'TransDocs.model.AbstractModel',

    entityName: "OrderTransport",
    fields: [
        {name: "driverId", reference: "Driver", mapping: "driver.objectId", unique: true},
        {name: "carId", reference: "Car", mapping: "car.objectId", unique: true},
        {name: "trailer", type: "string"},
        {name: "driverPassport", reference: "TransDocs.model.Passport"}
    ],

    getObjectType: function(){
        return "order_transport";
    }
});
