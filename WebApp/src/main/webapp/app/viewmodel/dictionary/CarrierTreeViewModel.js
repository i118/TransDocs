Ext.define("TransDocs.viewmodel.dictionary.CarrierTreeViewModel",{
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.carrierTree",

    requires: [
        "TransDocs.data.store.dictionary.CarrierTreeStore"
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
        carrierTreeStore: {
            type: "carrierTree",
            autoLoad: false
        }
    }
});