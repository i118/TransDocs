Ext.define("TransDocs.view.component.dictionary.CarPanel", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.carPanel",
    config: {
        modelClass: null
    },

    requires: [
      "TransDocs.controller.dictionary.CarController",
        "TransDocs.view.grid.dictionary.CarListView"
    ],

    layout: 'fit',

    isLoaded: false,

    controller: "carController",

    initComponent: function (config) {
        this.createItems();
        this.callParent(arguments);
    },

    createItems: function () {
        var me = this;
        this.items = [
            {
                xtype: 'panel',
                layout: 'fit',
                border: 0,
                listeners: {
                    disable: function (comp, eOpts) {
                        for (var i = 0; i < comp.items.length; i++) {
                            var item = comp.items.getAt(i);
                            if (item) {
                                item.setDisabled(true);
                            }
                        }
                    },

                    enable: function (comp, eOpts) {
                        for (var i = 0; i < comp.items.length; i++) {
                            var item = comp.items.getAt(i);
                            if (item) {
                                item.setDisabled(false);
                            }
                        }
                    }
                },
                items: [ {
                    xtype: 'panel',
                    layout: 'fit',
                    border: 0,
                    items: [
                        {
                            xtype: 'carListView',
                            contractor: me.getModel(),
                            modelClass: me.getModelClass(),
                            reference: "carListView",
                            border: 0
                        }
                    ]
                }
                ]
            }
        ]
    },

    loadRecord: function (data) {
        this.model = data;
        var driverList = this.getController().lookupReference("carListView");
        if (driverList && !this.isLoaded) {
            this.on('activate', function () {
                driverList.loadRecord(this.model);
                this.isLoaded = true;
            }, this);
        }
    },

    lookupStore: function(){
        return this.lookupViewModel().get("contractor").cars();
    }
});