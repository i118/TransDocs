Ext.define('TransDocs.data.reader.DefaultJsonReader',{
    extend: 'Ext.data.reader.Json',
    alias: 'reader.defaultjson',
    rootProperty: 'results',
    successProperty: 'success',
    totalProperty: 'totalCount',
    messageProperty: 'message',
    idProperty: 'objectId'
});