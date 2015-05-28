Ext.define('TransDocs.controller.dictionary.UserComponentController', {
    extend: 'TransDocs.controller.dictionary.AbstractDictionaryController',
    alias: 'controller.userComponent',
    requires: [
        "TransDocs.view.component.dictionary.UserForm",
        "TransDocs.view.component.dictionary.RoleComponent",
        "TransDocs.data.store.dictionary.UserStore"
    ],

    selectionChange: function (model, selected) {
        var userInfo = this.lookupReference("userinfo");
        if (userInfo) {
            userInfo.changeButtonState(selected);
        }
    },

    loadUserInfo: function (dataview, record, item, index, event) {
        if (record.isRoot()) return;
        var oldForm = this.lookupReference('userform');

        if (oldForm) {
            if (currentRecord == record) {
                return;
            }
            var currentRecord = oldForm.getViewModel().get("user");
            oldForm.close();
        }

        record.setDirty(true);
        var session = new Ext.data.Session();
        var userStore = Ext.create("TransDocs.data.store.dictionary.UserStore", {session: session});

        var userInfo = this.lookupReference('userinfo');
        userInfo.setLoading(true, true);
        userStore.load({
            id: record.getId(),
            single: true,
            callback: function (records, operation) {
                if (records.length == 1) {
                    var record = records[0];
                    var viewModel = {
                        session: session,
                        data: {
                            user: record,
                            isEditMode: false
                        },
                        stores: {
                            userStore: userStore
                        }
                    };
                    var userForm = Ext.create('TransDocs.view.component.dictionary.UserForm', {
                        visible: false,
                        model: record,
                        reference: 'userform',
                        session: session,
                        viewModel: viewModel
                    });
                    userInfo.add(userForm);
                    userForm.show();
                    userInfo.setLoading(false);
                }
            }
        });
    },

    addUser: function (button) {
        var userInfo = this.lookupReference('userinfo');
        var oldForm = this.lookupReference('userform');
        if (oldForm) {
            oldForm.close();
        }
        var session = new Ext.data.Session();
        var newUser = TransDocs.service.UserService.newUser(session);
        var userStore = Ext.create("TransDocs.data.store.dictionary.UserStore", {session: session});
        userStore.add(newUser);
        var viewModel = {
            session: session,
            data: {
                user: newUser,
                isEditMode: true
            },
            stores: {
                userStore: userStore
            }
        };
        var form = Ext.create('TransDocs.view.component.dictionary.UserForm', {
            visible: false,
            reference: 'userform',
            viewModel: viewModel,
            session: session
        });
        var userTree = this.lookupReference('usertree');
        if(userTree.getSelectionModel() && userTree.getSelectionModel().hasSelection()){
            userTree.getSelectionModel().deselectAll()
        }
        userInfo.changeButtonState(newUser);
        userInfo.add(form);
        form.show();
    },

    editUser: function (button) {
        var form = this.lookupReference('userform');
        var viewModel = form.getViewModel();
        viewModel.set("isEditMode", true);
    },

    deleteUser: function (button) {
        var form = this.lookupReference('userform');
        var viewModel = form.getViewModel();
        var user = viewModel.get("user");
        var userStore = viewModel.getStore("userStore");
        userStore.remove(user);
        var me = this;
        userStore.save({
            success: function (batch) {
                var userTree = me.lookupReference('usertree');
                userTree.getStore().reload();
                form.close();
            }
        });
    },

    restoreUser: function (button) {
        var userInfo = this.lookupReference('userinfo');
        userInfo.setLoading(true, true);
        var userForm = this.lookupReference('userform');
        var viewModel = userForm.getViewModel();
        var user = viewModel.get("user");
        var userStore = viewModel.getStore("userStore");
        if (user) {
            user.set('deleted', false);
            var me = this;
            userStore.save({
                success: function (batch) {
                    var userTree = me.lookupReference('usertree');
                    userTree.getStore().reload();
                },
                callback: function () {
                    userInfo.setLoading(false);
                }
            });
        }
    },

    saveUserForm: function (button) {
        var userInfo = this.lookupReference('userinfo');
        userInfo.setLoading(true, true);
        var userForm = this.lookupReference('userform');
        var viewModel = userForm.getViewModel();
        var userStore = viewModel.getStore("userStore");
        var user = viewModel.get("user");
        user.setDirty(true);
        if (!this.checkPassword(userForm)) {
            userInfo.setLoading(false);
            this.showInvalidPasswordMessage();
            return;
        }
        var password = userForm.getValues()['password'];
        if (password) {
            var proxy = userStore.getProxy();
            user.setDirty(true);
            proxy.setParamsAsJson(false);
            proxy.setExtraParam('password', password);
        }
        var me = this;
        userStore.save({
                success: function (batch) {
                    var userTree = me.lookupReference('usertree');
                    userTree.getStore().reload();
                    userForm.close();
                },
                callback: function () {
                    userInfo.setLoading(false);
                }
            }
        );
    },

    cancelUserForm: function (button) {
        var userForm = this.lookupReference('userform');
        userForm.cancelEditUser();
        userForm.close();
    },

    addRole: function (button) {
        var window = this.lookupReference('userDictionaryPanel');
        var userForm = this.lookupReference('userform');
        var session = userForm.lookupSession().spawn();
        Ext.create('TransDocs.view.component.dictionary.RoleComponent', {
            parent: window,
            reference: "rolecomponent"
        }).show();
    },

    deleteRole: function (button) {
        var roleListView = this.lookupReference('userrolelistview');
        var userForm = this.lookupReference('userform');
        var selection = roleListView.getSelectionModel().getSelection();
        var user = userForm.getViewModel().get("user");
        if (selection && selection.length > 0) {
            for (var index in selection) {
                user.roleModels().remove(selection[index]);
                user.setDirty(true);
            }
        }
    },

    addRoleToUser: function (button) {
        var roleComponent = this.lookupReference('rolecomponent');
        var roleListView = roleComponent.down('rolelistview');
        var selections = roleListView.getSelectionModel().getSelection();
        if (selections && selections.length > 0) {
            var userForm = this.lookupReference('userform');
            var viewModel = userForm.getViewModel();
            var currentUser = viewModel.get("user");
            if (currentUser) {
                for (var index in selections) {
                    currentUser.roleModels().add(selections[index]);
                    currentUser.setDirty(true);
                }
            }
        }
        roleComponent.close();
    },

    closeRoleComponent: function (button) {
        var roleComponent = this.lookupReference('rolecomponent');
        roleComponent.close();
    },

    checkPassword: function (userForm) {
        return userForm.getValues()['password'] == userForm.getValues()['checkPassword'];
    },

    showInvalidPasswordMessage: function () {
        Ext.MessageBox.show({
            title: 'Error!',
            msg: "Введенные пароли не совпадают.",
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK,
            resizable: true,
            overflowY: 'auto',
            overflowX: 'auto'
        });
    },

    lookupTreeStore: function(){
        return this.getView().getViewModel().getStore("userTree");
    },

    getSelected: function(){
        var tree = this.lookupReference('usertree');
        var selection = tree.getSelection();
        var selected;
        if (selection && selection.length > 0) {
            selected = selection[0];
        }

        return selected;
    }

});