Ext.define("TransDocs.model.Passport",{
    extend: "Ext.data.Model",

    fields: [
        {name: "serial", type: "string"},
        {name: "number", type: "string"},
        {name: "issuedPassport", type: "string"},
    ]
});