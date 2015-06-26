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
        'TransDocs.model.dictionary.CompanyModel',
        'TransDocs.model.document.OrderTransport',
        'TransDocs.model.document.OrderAdditional',
        'TransDocs.model.document.OrderAdditionalCondition'
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
        {name: "paymentDate", type: "string"},

        {name: "orderTransportId", reference: "OrderTransport", unique: true, mapping: "orderTransport.objectId"},
        {name: "orderAdditional", reference: "OrderAdditional"},
        {name: "customerAdditionalConditionId", reference: "OrderAdditionalCondition", unique: true, mapping: "customerAdditionalCondition.objectId"},
        {name: "carrierAdditionalConditionId", reference: "OrderAdditionalCondition", unique: true, mapping: "carrierAdditionalCondition.objectId"}
    ],

    getObjectType: function(){
        return"order_document";
    }
});