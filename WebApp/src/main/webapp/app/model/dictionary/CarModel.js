Ext.define("TransDocs.model.dictionary.CarModel",{
    extend: "TransDocs.model.AbstractModel",

    entityName: "Car",
    requires:[
        "TransDocs.model.dictionary.CarrierModel",
        "TransDocs.model.Passport"
    ],

    fields: [
        {name: "carBrand", type: "string"},
        {name: "carNumber", type: "string"},
        {name: "trailerBrand", type: "string"},
        {name: "trailerNumber", type: "string"},
        {name: "trailerType", type: "string"},
        {name: "capacity", type: "string"},
        {name: "cubage", type: "string"},
        {name: "carrierId", reference:{
            type: "Carrier",
            association: 'CarByCarrier',
            role: 'carrier',
            inverse: 'cars'
        }},
        {name: "description", convert: function(newValue, model){
            return model.get("carBrand")+", "+model.get("carNumber");
        }}
    ],

    proxy: {
        type: 'rest',
        api: {
            read: 'Carrier/get.cars'
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
        return "td_car";
    }
});