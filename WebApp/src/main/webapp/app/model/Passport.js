Ext.define("TransDocs.model.Passport",{
    extend: "Ext.data.Model",

    entityName: "Passport",
    fields: [
        {name: "serial", type: "string"},
        {name: "number", type: "string"},
        {name: "issuedPassport", type: "string"}
    ]
});