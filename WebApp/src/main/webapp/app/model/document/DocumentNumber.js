Ext.define("TransDocs.model.document.DocumentNumber", {
    extend: "TransDocs.model.EmbeddableModel",
    fields: [
        {name: "number", type: 'string'},
        {name: "numberDate", type: 'date', dateFormat: 'd.m.Y'}
    ],

    getFormatDate: function(){
        var v = this.get("numberDate");
        if (!Ext.isDate(v)) {
            v = new Date(Date.parse(v));
        }
        return Ext.Date.dateFormat(v, 'd.m.Y');
    }
});