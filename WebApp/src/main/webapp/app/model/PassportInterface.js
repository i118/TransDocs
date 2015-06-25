Ext.define("TransDocs.model.PassportInterface",{

    statics: {
        decorate: function(modelClass){
            var model = Ext.data.schema.Schema.lookupEntity(modelClass);
            model.addFields([
                {name: "serial", type: "string", mapping: "passport.serial"},
                {name: "number", type: "string", mapping: "passport.number"},
                {name: "issuedPassport", type: "string", mapping: "passport.issuedPassport"},
            ]);
        }
    }
});