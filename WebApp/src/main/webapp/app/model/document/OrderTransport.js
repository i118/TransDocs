/**
 * Created by zerotul on 26.06.15.
 */
Ext.define('TransDocs.model.document.OrderTransport', {
    extend: 'TransDocs.model.AbstractModel',

    requires: [
        'TransDocs.model.dictionary.DriverModel',
        'TransDocs.model.dictionary.CarModel'
    ],

    entityName: "OrderTransport",
    fields: [
        {name: "driverId", reference: "Driver", mapping: "driver.objectId", unique: true},
        {name: "carId", reference: "Car", mapping: "car.objectId", unique: true},
        {name: "trailer", type: "string"},
        {name: "driverPassport", reference: "Passport"},
        {name: "driverPhone", type: "string"}
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'OrderDocument/get.transport'
        },
        actionMethods: {
            read: 'get'
        },
        reader: {type: 'defaultjson'},
        listeners: {
            exception: function (proxy, response, operation) {
                Ext.MessageBox.show({
                    title: 'Error!',
                    msg: operation.getError() ? operation.getError() : "Ошибка связи с сервером",
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK,
                    resizable: true,
                    overflowY: 'auto',
                    overflowX: 'auto'
                });
            }
        }
    },

    getObjectType: function(){
        return "order_transport";
    }
});
