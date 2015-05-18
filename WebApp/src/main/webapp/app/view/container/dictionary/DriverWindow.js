Ext.define("TransDocs.view.container.dictionary.DriverWindow", {
    extend: "TransDocs.view.container.ChildWindow",
    config: {
        contractor: null,
        modelClass: null,
        parentController: null
    },
    createMode: false,

    bind: {
        title: '{record.lastName}'
    },

    listeners: {
        close: 'closeDriverForm'
    },

    requires: [
        "TransDocs.view.component.dictionary.DriverForm",
        "TransDocs.controller.dictionary.DriverFormController"
    ],

    controller: 'driverFormController',

    createItems: function () {
        var me = this;
        this.items = [
            {
                xtype: "driverForm",
                contractor: me.getContractor(),
                reference: 'driverForm',
                modelClass: me.getModelClass()
            }
        ];
    },

    loadRecord: function (record) {
        var driverForm = this.getController().lookupReference('driverForm');
        driverForm.loadRecord(record);
    }
});