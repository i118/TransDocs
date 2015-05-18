Ext.define("TransDocs.service.OrderDocumentService",{
    extend: 'Ext.Class',
    singleton: true,
    requires: [
        "TransDocs.model.document.OrderDocumentModel",
        "TransDocs.service.CompanyService",
        "TransDocs.service.UserService"
    ],

    constructor: function(){
      this.callParent(arguments);
    },

    createOrder: function(session){
       var order = session.createRecord("TransDocs.model.document.OrderDocumentModel");
        var currentUser = TransDocs.service.UserService.getCurrentUser();
        order.setManager(currentUser);
        order.setCompany(TransDocs.service.CompanyService.getCurrentCompany());
        return order;
    }
});