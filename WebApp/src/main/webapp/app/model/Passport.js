Ext.define("TransDocs.model.Passport",{
    extend: "TransDocs.model.EmbeddableModel",

    entityName: "Passport",
    fields: [
        {name: "serial", type: "string"},
        {name: "number", type: "string"},
        {name: "issuedPassport", type: "string"}
    ]
});