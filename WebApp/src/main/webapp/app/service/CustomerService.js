Ext.define("TransDocs.service.CustomerService", {
    extend: 'Ext.Base',
    singleton: true,
    requires: [
        "TransDocs.model.file.CustomerFileModel"
    ],
    constructor: function(){
        Ext.data.NodeInterface.decorate("TransDocs.model.file.CustomerFileModel");
        this.callParent(arguments);
    },

    newCustomer: function(session){
        var newCustomer = session.createRecord('TransDocs.model.dictionary.CustomerModel');
        var newFileStore = session.createRecord('TransDocs.model.file.CustomerFileModel');
        newFileStore.set("fileType", "STORE");
        newCustomer.setFileStore(newFileStore);
        newCustomer.persons().blockLoadCounter=1;
        var accountDetails = session.createRecord('TransDocs.model.dictionary.AccountDetails');
        newCustomer.setAccountDetails(accountDetails);
        return newCustomer;
    }
});