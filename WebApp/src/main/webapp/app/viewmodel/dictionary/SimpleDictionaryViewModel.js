Ext.define("TransDocs.viewmodel.dictionary.SimpleDictionaryViewModel",{
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.simpleDictionaryViewModel",
    requires: [
        "TransDocs.data.store.dictionary.SimpleDictionaryStore"
    ],
    data:{
        isEditMode: true,
        recordType: "com.td.model.entity.dictionary.dataset.SimpleDictionaryDataSet",
        restrictions: {
            deleted: {
                propertyName: "deleted",
                value: false,
                operator: "="
            },dictionaryType: {
                propertyName: "dictionaryType",
                value: null,
                operator: "="
            },
            description:{
                propertyName: "description",
                value: null,
                operator: "LIKE"
            }
        }
    },stores: {
        simpleDictionary: {
            type: "simpleDictionaryStore",
            autoLoad: true,
            session: true,

            listeners: {
                beforeload: 'applySpecification'
            }
        }
    }
});