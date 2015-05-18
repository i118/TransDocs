Ext.define('TransDocs.data.proxy.AbstractRestProxy',{
   extend: 'Ext.data.proxy.Rest',
   appendId: true,
   idParam: "objectId",
   timeout: 180000,
   headers: {
        'Content-Type': 'application/json;charset=utf-8'
   }
});