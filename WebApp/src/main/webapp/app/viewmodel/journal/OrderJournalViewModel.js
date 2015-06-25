Ext.define("TransDocs.viewmodel.journal.OrderJournalViewModel", {
    extend: 'Ext.app.ViewModel',
    alias: "viewmodel.orderJournal",
    requires: [
        "TransDocs.data.store.document.OrderJournalStore",
        "TransDocs.data.store.dictionary.CustomerStore",
        "TransDocs.data.store.dictionary.UserStore",
        "TransDocs.data.store.dictionary.CarrierStore",
        "TransDocs.service.UserService",
        "TransDocs.service.SpecificationService"
    ],

    constructor: function () {
        this.callParent(arguments);
        this.getStore("userStore").add(TransDocs.service.UserService.getCurrentUser());
        this.set("restrictions.manager.value", TransDocs.service.UserService.getCurrentUser().getId());
    },

    data: {
        recordType: "com.td.model.entity.document.dataset.OrderDocumentDataSetImpl",
        restrictions: {
            manager: {
                propertyName: "managerId",
                value: null,
                operator: "="
            },
            transportationType: {
                propertyName: "transportationType",
                value: null,
                operator: "="
            },
            customer: {
                propertyName: "customerId",
                value: null,
                operator: "="
            },
            carrier: {
                propertyName: "carrierId",
                value: null,
                operator: "="
            },
            incomingNumber: {
                propertyName: "incomingNumber",
                value: null,
                operator: "like"
            },
            outgoingNumber: {
                propertyName: "outgoingNumber",
                value: null,
                operator: "like"
            }
        },
        order: {
            fields: ["outgoingNumber"],
            orderType: "desc"
        }
    },
    stores: {
        orderStore: {
            type: "orderJournalStore",
            autoLoad: true,
            listeners: {
                beforeload: 'applySpecification'
            }
        },
        customerStore: {
            type: 'customerStore'
        },
        userStore: {
            type: 'userStore'
        },
        carrierStore: {
            type: 'carrierStore'

        }
    }
});