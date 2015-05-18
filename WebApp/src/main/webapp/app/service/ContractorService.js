Ext.define("TransDocs.service.ContractorService",{
    extend: 'Ext.Base',
    singleton: true,

    constructor: function () {
        this.callParent(arguments);
    },

    openPerson: function(person, parentWnd, session, isEditMode, isCreateMode){
        var viewModel =  {
            data:{
                record:person,
                isEditMode: isEditMode,
                isCreateMode: isCreateMode
            }
        }
        var wnd = Ext.create("TransDocs.view.container.dictionary.ContractPersonWindow", {
            autoWidth: true,
            autoHeight: true,
            parent: parentWnd,
            reference: 'personWindow',
            session: session,
            viewModel: viewModel
        });
        wnd.show();
    }
});