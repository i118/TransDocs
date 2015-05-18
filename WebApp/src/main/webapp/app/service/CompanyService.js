Ext.define("TransDocs.service.CompanyService",{
    extend: 'Ext.Base',
    singleton: true,

    requires: [
        "TransDocs.model.dictionary.CompanyModel",
        "TransDocs.data.store.dictionary.CompanyStore"
    ],

    currentCompany: null,

    constructor: function () {
        Ext.data.NodeInterface.decorate("TransDocs.model.dictionary.CompanyModel");
        this.callParent(arguments);
        var companyStore = Ext.create("TransDocs.data.store.dictionary.CompanyStore");
        var me = this;
        companyStore.load({
            id: 'current',
            callback:function(records, operation, success){
                if(success) {
                    me.currentCompany = records[0];
                }else{
                    throw "current company not loaded";
                }
            }
        })
    },

    getCurrentCompany: function(){
        if(!this.currentCompany){
            throw "current company not found";
        }
        return this.currentCompany;
    },

    newCompany: function(session){
        var company = session.createRecord("TransDocs.model.dictionary.CompanyModel");
        return company;
    }
});