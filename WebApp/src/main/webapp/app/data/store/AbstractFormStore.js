Ext.define("TransDocs.data.store.AbstractFormStore", {
    extend: "Ext.data.Store",
    timeout: 180000,
    autoLoad: false,
    autoDestroy: true,

    setExtraParams: function (params) {
        this.getProxy().setExtraParams(params);
    }
});