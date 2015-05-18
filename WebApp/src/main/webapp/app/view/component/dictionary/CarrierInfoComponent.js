Ext.define("TransDocs.view.component.dictionary.CarrierInfoComponent", {
    extend: "TransDocs.view.component.AbstractComponent",
    alias: 'widget.carrierInfo',
    requires: [
        'TransDocs.view.component.dictionary.ContractorForm',
        'TransDocs.view.component.dictionary.AccountDetailsForm',
        'TransDocs.view.component.dictionary.ContractPersonPanel',
        'TransDocs.model.dictionary.AccountDetails',
        'TransDocs.model.dictionary.CarrierPersonModel',
        'TransDocs.model.dictionary.CarrierModel',
        'TransDocs.model.dictionary.DriverModel',
        'TransDocs.view.component.dictionary.DriverPanel',
        'TransDocs.view.component.TDButton',
        'TransDocs.view.component.file.FileForm',
        'TransDocs.view.component.dictionary.CarPanel',
        'TransDocs.model.dictionary.CarModel'
    ],
    layout: 'fit',
    autoLoadRecord: false,

    dockedItems: {
        xtype: 'toolbar',
        dock: 'bottom',
        bind: {
            hidden: '{!isEditMode}'
        },
        items: [
            '->',
            {
                xtype: 'button',
                text: 'Отменить',
                action: 'CancelCarrier',
                handler: "cancelContractor"
            }, {
                xtype: 'button',
                text: 'Сохранить',
                action: 'SaveCarrier',
                handler: "saveContractor"
            }
        ]
    },

    items: [
        {
            xtype: 'contractorform',
            title: 'Клиент',
            lazyLoad: false
        }, {
            xtype: 'accountDetailsForm',
            title: 'Реквизиты'
        }
        , {
            xtype: 'contractPersonPanel',
            title: 'Контактные лица',
            modelClass: 'TransDocs.model.dictionary.CarrierPersonModel'
        }, {
            xtype: 'driverPanel',
            title: 'Водители',
            modelClass: 'TransDocs.model.dictionary.DriverModel'
        }, {
            xtype: 'carPanel',
            title: 'Машины',
            modelClass: 'TransDocs.model.dictionary.CarModel'
        }, {
            xtype: "fileForm",
            title: "Файлы",
            modelClass: "TransDocs.model.file.CarrierFileModel"
        }
    ],

    enable: function () {
        var viewModel = this.getViewModel();
        viewModel.set("isEditMode", true);
    },

    disable: function () {
        var viewModel = this.getViewModel();
        viewModel.set("isEditMode", false);
    }
});
