Ext.define('TransDocs.controller.AdminWorkSpaceController', {
    extend: 'Ext.app.Controller',

    requires: [
        "TransDocs.service.AdminComponentFactory"
    ],

    views: [
        'TransDocs.view.grid.admin.AdminSectionListView'
    ],
    stores: [
        'TransDocs.data.store.AdminSectionStore'
    ],

    refs: [
        {
            ref: 'TransDocs.view.grid.admin.AdminSectionListView',
            selector: 'adminSectionList'
        }
    ],

    init: function () {
        this.control({
            'adminSectionList': {
                itemclick: this.openSection
            }
        });
    },

    openSection: function (grid, record) {
        var wndConf = TransDocs.service.AdminComponentFactory.getComponent(record);
        TransDocs.util.WindowManager.openWindow(wndConf);

    }
});