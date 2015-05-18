Ext.define("TransDocs.data.store.document.OrderJournalStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "OrderDocumentController"
    },

    alias: 'store.orderJournalStore',

    remoteSort: true,

    autoLoad: false,

    fields: [
        {name: "incomingNumber", type: "string"},
        {name: "outgoingNumber", type: "string"},
        {name: "objectId", type: "string"},
        {name: "transportationType", type: "string"},
        {name: "customerFullName", type: "string"},
        {name: "carrierFullName", type: "string"},
        {name: "managerFullName", type: "string"}
    ],


    initApi: function () {
        var me = this;
        this.getProxy().setApi({
            read: me.controllerName + '/find.dataset'
        });
        this.getProxy().setActionMethods({create: 'POST', read: 'POST', update: 'PUT', destroy: 'DELETE'});
        this.getProxy().setParamsAsJson(true);
        if(me.extraParams){
            this.getProxy().setExtraParams(me.extraParams);
        }
    },

    setExtraParams: function(params){
        this.extraParams = params;
        this.getProxy().setExtraParams(this.extraParams);
    }
});