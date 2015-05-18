Ext.define('TransDocs.view.grid.dictionary.UserRoleListView', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userrolelistview',
    autoWidth: true,
    autoHeight: true,
    enabled: true,

    bind:{store:'{user.roleModels}'},

    requires: [
        "TransDocs.model.dictionary.UserModel"
    ],

    tbar: [
        {
            xtype: 'button',
            text: 'Добавить',
            action: 'addRole',
            handler: "addRole",
            bind: {
                disabled: '{!isEditMode}'
            }
        },
        {
            xtype: 'button',
            text: 'Удалить',
            action: 'deleteRole',
            handler: 'deleteRole',
            bind: {
                disabled: '{!isEditMode}'
            }
        }
    ],

    columns: [
        {
            text: '',
            width: 0,
            dataIndex: 'objectId',
            hidden: true
        }, {
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
    ]

});