Ext.define("TransDocs.viewmodel.dictionary.CustomerTreeViewModel",{
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.customerTree",
    requires: [
        "TransDocs.data.store.dictionary.CustomerTreeStore"
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
        customerTree: {
            type: "customerTree",
            autoLoad: false
        }
    }
});