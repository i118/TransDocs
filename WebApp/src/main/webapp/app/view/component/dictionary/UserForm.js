Ext.define('TransDocs.view.component.dictionary.UserForm', {
    extend: 'TransDocs.view.component.AbstractForm',
    alias: 'widget.userform',
    layout: 'anchor',
    defaultType: 'textfield',
    method: 'post',
    overflowY: 'auto',
    store: null,
    config: {
        user: null
    },
    lazyLoad: false,
    requires:[
        'TransDocs.view.grid.dictionary.UserRoleListView',
        "TransDocs.model.dictionary.UserModel"
    ],

    border: false,
    dockedItems: {
        xtype: 'toolbar',
        dock: 'bottom',
        bind: {
            hidden: '{!isEditMode}'
        },
        items:[
            '->',
            {
                xtype: 'button',
                text: 'Отменить',
                action: 'cancelUserForm',
                handler: 'cancelUserForm'
            },
            {
                xtype: 'button',
                text: 'Сохранить',
                action: 'saveUserForm',
                handler: 'saveUserForm'
            }
        ]
    },


    items :[
        {
            fieldLabel: 'Имя',
            bind:{
                value: '{user.firstName}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            fieldLabel: 'Фамилия',
            bind:{
                value: '{user.lastName}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            fieldLabel: 'Отчество',
            bind: {
                value: '{user.patronymic}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            fieldLabel: 'Логин',
            bind: {
                value: '{user.login}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            fieldLabel: 'Пароль',
            name: 'password',
            bind: {
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            inputType: 'password',
            flex: 1
        },
        {
            fieldLabel: 'Повтор пароля',
            name: 'checkPassword',
            bind: {
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            inputType: 'password',
            flex: 1
        },
        {
            xtype: 'combobox',
            fieldLabel: 'Пол',
            bind: {
                value: '{user.gender}',
                editable: '{isEditMode}',
                hideTrigger: '{!isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1,
            queryMode: 'local',
            displayField: 'name',
            valueField: 'key',
            store:{
                xtype: 'store',
                fields: ['key', 'name'],
                data: [
                    {
                        key: 'MAN' ,name: 'Муж'
                    },
                    {
                        key: 'WOMAN', name: 'Жен'
                    }
                ]
            }
        },
        {
            fieldLabel: 'Телефон',
            bind: {
                value: '{user.phone}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            fieldLabel: 'E-Mail',
            bind: {
                value: '{user.mail}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },
        {
            xtype: 'fieldset',
            title: 'Роли',
            maxWidth: 280,
            height: 250,
            layout: 'fit',
            style:{
                marginLeft: '9%',
                marginTop: 10
            },
            items:[{
                xtype: 'panel',
                bodyStyle: {
                    background: '#dfe8f6'
                },
                layout:'fit',
                style:{
                    marginBottom: 5
                },
                items: [
                    {
                        xtype:"userrolelistview",
                        margin : 5,
                        reference: "userrolelistview"
                    }
                ]
            }]
        }
    ],


    getStore: function(){
        return this.store;
    },


    cancelEditUser: function(){
        var user = this.getViewModel().get('user');
        user.reject();
        user.setDirty(false);
    }
});