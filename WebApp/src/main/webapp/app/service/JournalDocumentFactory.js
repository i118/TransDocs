Ext.define("TransDocs.service.JournalDocumentFactory",{
    extend: 'Ext.Class',
    singleton: true,
    builders: Ext.create("Ext.util.HashMap"),

    requires: [
        "TransDocs.controller.journal.OrderJournalController",
        "TransDocs.view.component.journal.OrderJournalComponent",
        "TransDocs.data.store.document.OrderJournalStore",
        "TransDocs.viewmodel.journal.OrderJournalViewModel"
    ],

    constructor: function(){
        this.callParent(arguments);
        this.builders.add("td_order_journal", this.orderJournalComponent);
    },

    getJournalComponent: function (record, customConfig) {
        var builder = this.builders.get(record.get("journalType"));
        if(!builder){
            Ext.Msg.show({
                title: 'Журналы',
                msg: 'Компонент для журнала "'+record.get("description")+'" не найден',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.INFO
            });
            return;
        }
        return builder.call(this, record, customConfig);
    },

    orderJournalComponent: function(record, customConfig){
        var currentUser = TransDocs.service.UserService.getCurrentUser();

        var wndConf = {title: record.get("description"),
            width: 620, height: 500, controller: "orderJournalController",maximized:true,
            viewModel: {
                type: "orderJournal"
            },
            stateId: "orderJournal",
            scope: record.get("objectId"), items: [
                {
                    xtype: 'orderJournalComponent',
                    reference:"orderJournalComponent"
                }
            ], layout: 'fit'
        };
        if(customConfig) {
            Ext.apply(wndConf, customConfig)
        }
        return wndConf;
    }
})