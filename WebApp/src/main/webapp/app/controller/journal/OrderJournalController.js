Ext.define("TransDocs.controller.journal.OrderJournalController", {
    extend: "TransDocs.controller.journal.AbstractJournalController",
    alias: "controller.orderJournalController",

    requires: [
        "TransDocs.service.OrderDocumentService",
        "TransDocs.view.component.document.OrderDocumentComponent",
        "TransDocs.controller.document.OrderDocumentController",
        "TransDocs.data.proxy.dictionary.CustomerTreeProxy",
        "TransDocs.data.store.dictionary.CustomerStore",
        "TransDocs.data.store.dictionary.UserStore",
        "TransDocs.data.store.dictionary.CarrierStore",
        "TransDocs.data.store.document.OrderDocumentStore",
        "TransDocs.data.store.dictionary.SimpleDictionaryStore",
        "TransDocs.data.store.dictionary.CompanyStore"
    ],

    createNewDocument: function () {
        var session = new Ext.data.Session();
        var document = TransDocs.service.OrderDocumentService.createOrder(session);
        var orderStore = Ext.create("TransDocs.data.store.document.OrderDocumentStore", {session: session});
        orderStore.add(document);
        var me = this;
        var viewModel = {
            session: session,
            data: {
                document: document,
                customer: null,
                carrier: null
            },
            stores:me.getStores(session, orderStore)
        };
        var wndConfig = {
            viewModel: viewModel,
            session: session,
            autoWidth: true,
            autoHeight: true,
            width: 830,
            height: 600,
            scroll: 'auto',
            controller: 'orderDocumentController',
            callerComponent: me.getView(),
            scope: document.getId(),
            layout: 'fit',
            reference: 'orderWindow',
            title: "Новая заявка: Компонент в разработке",
            items: [
                {
                    xtype: 'orderDocumentComponent',
                    reference: 'orderDocumentComponent'
                }
            ]
        };

        TransDocs.util.WindowManager.openWindow(wndConfig);
    },

    openDocument: function (grid, record) {
        var session = new Ext.data.Session();
        var orderStore = Ext.create("TransDocs.data.store.document.OrderDocumentStore", {session: session});
        var me = this;
        orderStore.load({
            id: record.get("objectId"),
            callback: function (records, operation, success) {
                if (success) {
                    var document = records[0];
                    if (document.getCustomer())document.getCustomer().persons().complete = true;
                    if (document.getCarrier())document.getCarrier().persons().complete = true;
                    var viewModel = {
                        session: session,

                        data: {
                            document: document,
                            customer: document.getCustomer(),
                            carrier: document.getCarrier()
                        },
                        stores:me.getStores(session, orderStore)
                    };
                    var wndConfig = {
                        viewModel: viewModel,
                        session: session,
                        width: 830,
                        height: 600,
                        callerComponent: me.getView(),
                        scroll: 'auto',
                        controller: 'orderDocumentController',
                        scope: records[0].getId(),
                        layout: 'fit',
                        reference: 'orderWindow',
                        title: "Заявка № " + document.getOutgoingNumber().get("number") + " от " + document.getOutgoingNumber().getFormatDate()+": Компонент в разработке",
                        items: [
                            {
                                xtype: 'orderDocumentComponent',
                                reference: 'orderDocumentComponent'
                            }
                        ]
                    };

                    TransDocs.util.WindowManager.openWindow(wndConfig);
                }
            }
        });
    },

    getStores: function(session, orderStore){
        return {
            customerStore: {
                session: session,
                type: 'customerStore'
            },
            userStore: {
                type: 'userStore',
                session: session
            },
            carrierStore: {
                type: 'carrierStore',
                session: session
            },
            orderTitleStore: {
                type: 'simpleDictionaryStore',
                session: session
            },
            paymentDateStore: {
                type: 'simpleDictionaryStore',
                session: session
            },
            transportTypeStore: {
                type: 'simpleDictionaryStore',
                session: session
            },
            companyStore: {
                type: 'companyStore',
                session: session
            },
            orderStore: orderStore
        };
    },

    lookupJournalStore: function () {
        var journalViewModel = this.getView().lookupViewModel();
        var journalOrderStore = journalViewModel.getStore("orderStore");
        return journalOrderStore;
    }
});