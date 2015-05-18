Ext.define("TransDocs.service.RenderFactory", {
    extend: 'Ext.Class',
    singleton: true,

    constructor: function(){
        this.callParent(arguments);
    },

    genderRender: function(value, metaData, record){
        if(!value)return "";
        if(value=="MAN"){
            return "Муж."
        }
        return "Жен."
    },

    transportationTypeRender: function(value, metaData, record){
        if(!value)return "";

        if(value == "INTERCITY") return "Междугородняя";
        if(value == "CITY") return "Городская";
        if(value == "SUBURBAN") return "Пригородная";
        if(value == "INTERNATIONAL") return "Международная";
        if(value == "PASSENGER") return "Пассажирская";

        return "";
    }
});