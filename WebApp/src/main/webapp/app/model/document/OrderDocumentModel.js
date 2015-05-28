Ext.define("TransDocs.model.document.OrderDocumentModel", {
    extend: "TransDocs.model.document.AbstractDocumentModel",

    entityName: "OrderDocument",
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
        {name: "incomingNumber", reference: "TransDocs.model.document.DocumentNumber"},
        {name: "outgoingNumber", reference: "TransDocs.model.document.DocumentNumber"},
        {name: "transportationType", type: "string"},
        {name: "customerPaymentMethod", type: "string"},
        {name: "managerId", reference: "User", mapping: "manager.objectId", unique: true},
        {name: "customerId", reference: "Customer", mapping: "customer.objectId", unique: true},
        {name: "carrierId", reference: "Carrier", mapping: "carrier.objectId", unique: true},
        {name: "customerPersonId", reference: "CustomerPerson",mapping: "customerPerson.objectId", unique: true},
        {name: "carrierPersonId", reference: "CarrierPerson", mapping: "carrierPerson.objectId", unique: true},
        {name: "companyId", reference: "Company", mapping: "company.objectId", unique: true},

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
            this.set("customerPhone", this.getCustomer().get("phone"));
            this.set("customerAddress", this.getCustomer().get("legalAddress"));
            this.set("customerEmail", this.getCustomer().get("email"));
        }else{
            this.set("customerPhone", null);
            this.set("customerAddress", null);
            this.set("customerEmail", null);
        }
    },

    setCarrier: function(carrier){
        this.callParent(arguments);
        if(carrier) {
            this.set("carrierPhone", this.getCarrier().get("phone"));
            this.set("carrierAddress", this.getCarrier().get("email"));
            this.set("carrierEmail", this.getCarrier().get("legalAddress"));
        }
    }
});