Ext.define("TransDocs.view.container.dictionary.CarWindow", {
    extend: "TransDocs.view.container.ChildWindow",
    config: {
        contractor: null,
        modelClass: null,
        parentController: null
    },

    requires: [
        "TransDocs.view.component.dictionary.CarForm",
        "TransDocs.controller.dictionary.CarFormController"
    ],

    listeners: {
        close: 'closeCarForm'
    },

    controller: 'carFormController',

    createItems: function () {
        var me = this;
        this.items = [
            {
                xtype: "carForm",
                contractor: me.getContractor(),
                reference: 'carForm',
                modelClass: me.getModelClass()
            }
        ];
    }
});