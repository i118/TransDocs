Ext.define("TransDocs.view.grid.dictionary.RoleListView",{
    extend: 'Ext.grid.Panel',
    alias:'widget.rolelistview',
    autoWidth: true,
    autoHeight: true,
    enabled: true,
    selType: 'checkboxmodel',
    requires: [
        "TransDocs.data.store.dictionary.RoleListViewStore"
    ],
    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        },{
            text: '',
            width: 0,
            dataIndex: 'role_name',
            hidden: true
        },
        {
            text: 'Наименование',
            dataIndex: 'description',
            flex: 1
        }

    ],

    store: {
        type:"roleStore"
    }

});