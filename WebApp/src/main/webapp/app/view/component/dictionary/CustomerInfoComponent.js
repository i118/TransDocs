Ext.define("TransDocs.view.component.dictionary.CustomerInfoComponent",{
    extend: "TransDocs.view.component.AbstractComponent",
    alias: 'widget.customerinfo',
    requires:[
        'TransDocs.view.component.dictionary.ContractorForm',
        'TransDocs.view.component.dictionary.AccountDetailsForm',
        'TransDocs.view.component.dictionary.ContractPersonPanel',
        'TransDocs.model.dictionary.AccountDetails',
        'TransDocs.model.dictionary.CustomerModel',
        'TransDocs.model.dictionary.CustomerPersonModel',
        'TransDocs.model.dictionary.DriverModel',
        'TransDocs.view.component.TDButton',
        'TransDocs.view.component.file.FileForm'
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
                action: 'CancelCustomer',
                handler: "cancelContractor"
            },{
                xtype: 'button',
                text: 'Сохранить',
                action: 'SaveCustomer',
                handler: "saveContractor"
            }
        ]
    },

    items: [
        {
            xtype: 'contractorform',
            title: 'Клиент',
            lazyLoad:false
        }, {
            xtype: 'accountDetailsForm',
            title: 'Реквизиты'
        },{
            xtype: 'contractPersonPanel',
            title: 'Контактные лица',
            modelClass: 'TransDocs.model.dictionary.CustomerPersonModel'
        },{
            xtype: "fileForm",
            title: "Файлы",
            modelClass: "TransDocs.model.file.CustomerFileModel"
        }
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