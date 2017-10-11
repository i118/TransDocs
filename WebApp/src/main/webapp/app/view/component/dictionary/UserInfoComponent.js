Ext.define("TransDocs.view.component.dictionary.UserInfoComponent", {
    extend: "Ext.panel.Panel",
    alias: 'widget.userinfo',

    layout: 'fit',

    requires: [
        'TransDocs.view.component.dictionary.UserForm',
        'TransDocs.view.component.TDButton'
    ],

    dockedItems: {
        xtype: 'toolbar',
        dock: 'top',
        bind:{
            hidden: '{!isEditMode}'
        },
        items:[
            {
                xtype: 'tdbutton',
                text: 'Добавить',
                action: 'CreateUserAction',
                handler: 'addUser'
            },
            {
                xtype: 'tdbutton',
                text: 'Редактировать',
                action: 'EditUserAction',
                enabled: false,
                handler: 'editUser'
            },
            {
                xtype: 'tdbutton',
                text: 'Удалить',
                action: 'DeleteUserAction',
                enabled: false,
                handler: 'deleteUser'
            }, {
                xtype: 'tdbutton',
                text: 'Восстановить',
                action: 'RestoreUserAction',
                hidden: true,
                handler: 'restoreUser'
            }, "-",
            {
                xtype: 'checkboxgroup',

                layout: 'hbox',
                padding: 5,
                items: [
                    {
                        xtype: 'checkbox',
                        boxLabel: 'Удаленные пользователи',
                        labelAlign: 'right',
                        action: 'ShowDeletedUsers',
                        handler: "showDeletedDictionary"
                    }
                ]
            }
        ]
    },


    changeButtonState: function (selected) {
        var editButton = this.down("button[action=EditUserAction]");
        var deleteButton = this.down("button[action=DeleteUserAction]");
        var restoreButton = this.down("button[action=RestoreUserAction]");
        if (selected[0] && !selected[0].isRoot()) {
            editButton.targetObject = selected[0];
            editButton.enable();
            if (selected[0].get('deleted')) {
                restoreButton.targetObject = selected[0];
                restoreButton.enable();
                deleteButton.hide();
                restoreButton.show();
            } else {
                deleteButton.targetObject = selected[0];
                deleteButton.enable();
                restoreButton.hide();
                deleteButton.show();
            }
        } else {
            if (restoreButton.isVisible()) {
                restoreButton.hide();
                deleteButton.show();
            }
            editButton.disable();
            deleteButton.disable();
            restoreButton.disable();
        }
    }
});
