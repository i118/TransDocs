Ext.define("TransDocs.view.component.dictionary.DriverPanel", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.driverPanel",
    config: {
        modelClass: null
    },

    layout: 'fit',

    isLoaded: false,

    requires: [
        "TransDocs.view.grid.dictionary.DriverListView",
        "TransDocs.view.component.dictionary.DriverForm",
        "TransDocs.controller.dictionary.DriverController"
    ],

    controller: "driverController",

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
                    region: 'center',
                    border: 0,
                    items: [
                        {
                            xtype: 'driverListView',
                            contractor: me.getModel(),
                            modelClass: me.getModelClass(),
                            reference: "driverListView",
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
        var driverList = this.getController().lookupReference("driverListView");
        if (driverList && !this.isLoaded) {
            this.on('activate', function () {
                driverList.loadRecord(this.model);
                this.isLoaded = true;
            }, this);
        }
    },

    lookupStore: function(){
        return this.lookupViewModel().get("contractor").drivers();
    }
});