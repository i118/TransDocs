Ext.define("TransDocs.model.document.OrderDocumentModel", {
    extend: "TransDocs.model.document.AbstractDocumentModel",

    requires: [
        'TransDocs.model.document.DocumentNumber',
        'TransDocs.model.dictionary.UserModel',
        'TransDocs.model.dictionary.CarrierModel',
        'TransDocs.model.dictionary.CustomerModel',
        'TransDocs.model.dictionary.CustomerPersonModel',
        'TransDocs.model.dictionary.CarrierPersonModel',
        'TransDocs.model.dictionary.CompanyModel'
    ],

    fields:[
        {name: "incomingNumber", reference: "TransDocs.model.document.DocumentNumber", unique: true},
        {name: "outgoingNumber", reference: "TransDocs.model.document.DocumentNumber", unique: true},
        {name: "transportationType", type: "string"},
        {name: "customerPaymentMethod", type: "string"},
        {name: "manager", reference: "TransDocs.model.dictionary.UserModel"},
        {name: "customer", reference: "TransDocs.model.dictionary.CustomerModel", unique: true},
        {name: "carrier", reference: "TransDocs.model.dictionary.CarrierModel", unique: true},
        {name: "customerPerson", reference: "TransDocs.model.dictionary.CustomerPersonModel", unique: true},
        {name: "carrierPerson", reference: "TransDocs.model.dictionary.CarrierPersonModel", unique: true},
        {name: "company", reference: "TransDocs.model.dictionary.CompanyModel", unique: true},

        {name: "customerPhone", type: "string"},
        {name: "customerAddress", type: "string"},
        {name: "customerEmail", type: "string"},

        {name: "carrierPhone", type: "string"},
        {name: "carrierAddress", type: "string"},
        {name: "carrierEmail", type: "string"},
        {name: "carrierPaymentMethod", type: "string"},

        {name: "title", type: "string"},
        {name: "paymentDate", type: "string"}
    ],

    getObjectType: function(){
        return"order_document";
    },

    setCustomer: function(customer){
        this.callParent(arguments);
        if(customer) {
            this.set("customerPhone", customer.get("phone"));
            this.set("customerAddress", customer.get("legalAddress"));
            this.set("customerEmail", customer.get("email"));
        }else{
            this.set("customerPhone", null);
            this.set("customerAddress", null);
            this.set("customerEmail", null);
        }
    },

    setCarrier: function(carrier){
        this.callParent(arguments);
        if(carrier) {
            this.set("carrierPhone", carrier.get("phone"));
            this.set("carrierAddress", carrier.get("email"));
            this.set("carrierEmail", carrier.get("legalAddress"));
        }
    }
});