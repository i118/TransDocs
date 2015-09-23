Ext.define("TransDocs.controller.journal.OrderJournalController", {
    extend: "TransDocs.controller.journal.AbstractJournalController",
    alias: "controller.orderJournalController",

    requires: [
        "TransDocs.service.OrderDocumentService",
        "TransDocs.view.component.document.OrderDocumentComponent",
        "TransDocs.controller.document.OrderDocumentController",
        "TransDocs.viewmodel.document.OrderDocumentViewModel",
        "Ext.data.identifier.Uuid",
        "TransDocs.view.container.document.OrderDocumentWindow"
    ],

    createNewDocument: function () {
        var session = new Ext.data.Session();
        var me = this;
        var viewModel = {
            type: "orderdocument",
            parent: null,
            session: session,
            links: {
                document: {
                    type: "OrderDocument",
                    create: {
                        managerId: TransDocs.service.UserService.getCurrentUser().getId(),
                        companyId: TransDocs.service.CompanyService.getCurrentCompany().getId()
                    }
                }
            }
        };
        var wndConfig = {
            viewModel: viewModel,
            type: "TransDocs.view.container.document.OrderDocumentWindow",
            session: session,
            autoWidth: true,
            autoHeight: true,
            autoMask: false,
            width: 830,
            height: 600,
            scroll: 'auto',
            controller: 'orderDocumentController',
            callerComponent: me.getView(),
            scope: Ext.data.identifier.Uuid.create().generate(),
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
        var me = this;
        var viewModel = {
            type: "orderdocument",
            session: session,
            parent: null,
            links: {
                document: {
                    type: 'OrderDocument',
                    id: record.get("objectId")
                }
            }
        };
        var wndConfig = {

            viewModel: viewModel,
            session: session,
            type: "TransDocs.view.container.document.OrderDocumentWindow",
            width: 830,
            height: 600,
            callerComponent: me.getView(),
            scroll: 'auto',
            controller: 'orderDocumentController',
            scope: record.get("objectId"),
            layout: 'fit',
            reference: 'orderWindow',
            title: 'Заявка № ' + record.get("outgoingNumber") + ': Компонент в разработке',
            items: [
                {
                    xtype: 'orderDocumentComponent',
                    reference: 'orderDocumentComponent'
                }
            ]
        };

        TransDocs.util.WindowManager.openWindow(wndConfig);
    },

    lookupJournalStore: function () {
        var journalViewModel = this.getView().lookupViewModel();
        var journalOrderStore = journalViewModel.getStore("orderStore");
        return journalOrderStore;
    }

});