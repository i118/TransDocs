Ext.define("TransDocs.util.ApplicationContext",{
   extend: 'Ext.Class',
   sessionId: undefined,
   baseUrl: undefined,

   constructor: function(config){
       for(var propertyName in config){
           this[propertyName] = config[propertyName];
       }
       this.callParent(arguments);
   },

   getSessionId: function(){
       return this.sessionId;
   },

   getBaseUrl: function(){
       return this.baseUrl;
   }

})