Ext.define("TransDocs.viewmodel.dictionary.UserTreeViewModel",{
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.userTree",
    requires: [
        "TransDocs.data.store.dictionary.UserTreeStore"
    ],
    data:{
        isEditMode: true,
        recordType: "com.td.model.entity.dictionary.dataset.UserDataSet",
        restrictions: {
            deleted: {
                propertyName: "deleted",
                value: false,
                operator: "="
            },
            description:{
                propertyName: "lastName",
                value: null,
                operator: "LIKE"
            }
        }
    },stores: {
        userTree: {
            type: "userTreeStore",
            autoLoad: false
        }
    }
});