Ext.define('TransDocs.data.writer.DefaultJsonWriter',{
    extend:'Ext.data.writer.Json',
    alias: 'writer.defaultjson',
    writeAllFields: true,
    encode: false,
    expandData: true,
    nameProperty: "mapping"
});