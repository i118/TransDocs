Ext.define("TransDocs.view.grid.admin.AdminSectionListView",{
    extend: 'Ext.grid.Panel',
    autoWidth: true,
    autoHeight: true,
    hideHeaders: true,
    alias:'widget.adminSectionList',
    config: {
        width: 300,
        height: 300,
        enabled: true
    },
    requires: [
        "TransDocs.data.store.AdminSectionStore"
    ],
    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        },
        {
            dataIndex: 'description',
            flex: 1
        }
    ],
    store: {
        type: "adminStore"
    }
});