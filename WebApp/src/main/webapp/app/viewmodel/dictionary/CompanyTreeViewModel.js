Ext.define("TransDocs.viewmodel.dictionary.CompanyTreeViewModel",{
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.companyTree",

    requires: [
        "TransDocs.data.store.admin.CompanyTreeStore"
    ],
    data:{
        isEditMode: true,
        recordType: "com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl",
        restrictions: {
            deleted: {
                propertyName: "deleted",
                value: false,
                operator: "="
            },
            description:{
                propertyName: "description",
                value: null,
                operator: "LIKE"
            }
        }
    },stores: {
        companyTreeStore: {
            type: "companyTree",
            autoLoad: false
        }
    }
});