Ext.define("TransDocs.view.component.dictionary.CompanyComponent",{
    extend: "TransDocs.view.component.AbstractComponent",
    alias: 'widget.companyComponent',
    requires:[
        'TransDocs.view.component.dictionary.CompanyForm',
        'TransDocs.view.component.dictionary.AccountDetailsForm',
        'TransDocs.model.dictionary.CompanyModel',
        'TransDocs.model.dictionary.AccountDetails',
        "TransDocs.view.component.dictionary.CompanyUserPanel"
    ],
    layout: 'fit',
    autoLoadRecord: false,

    dockedItems:{
        xtype: 'toolbar',
        dock: 'bottom',
        bind:{
            hidden: '{!isEditMode}'
        },
        items:[
            '->',
            {
                xtype: 'button',
                text: 'Отменить',
                action: 'CancelCompany',
                handler: "cancelContractor"
            },{
                xtype: 'button',
                text: 'Сохранить',
                action: 'SaveCompany',
                handler: "saveContractor"
            }
        ]
    },

    items: [
        {
            xtype: 'companyform',
            title: 'Компания',
            lazyLoad:false
        }, {
            xtype: 'accountDetailsForm',
            title: 'Реквизиты'
        }
        //,{
        //    xtype: 'companyUserPanel',
        //    title: 'Пользователи'
        //}
    ],

    enable: function(){
        var viewModel = this.getViewModel();
        viewModel.set("isEditMode", true);
    },

    disable: function(){
        var viewModel = this.getViewModel();
        viewModel.set("isEditMode", false);
    }
});