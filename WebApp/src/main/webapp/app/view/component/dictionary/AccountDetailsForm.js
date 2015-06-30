Ext.define("TransDocs.view.component.dictionary.AccountDetailsForm",{
    extend: 'TransDocs.view.component.AbstractForm',
    layout: 'anchor',
    alias: 'widget.accountDetailsForm',
    defaultType: 'textfield',
    method: 'post',
    overflowY: 'auto',
    items: [
        {
            fieldLabel: 'ИНН',
            name: 'inn',
            bind: {
                value:'{contractor.accountDetails.inn}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'КПП',
            name: 'kpp',
            bind: {
                value: '{contractor.accountDetails.kpp}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'БИК',
            name: 'bic',
            bind: {
                value: '{contractor.accountDetails.bic}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОКПО',
            name: 'okpo',
            bind: {
                value: '{contractor.accountDetails.okpo}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОГРН',
            name: 'ogrn',
            bind: {
                value: '{contractor.accountDetails.ogrn}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОКВЭД',
            name: 'okved',
            bind: {
                value: '{contractor.accountDetails.okved}',
                editable: '{isEditMode}',
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Банк',
            name: 'bank',
            bind: {
                value: '{contractor.accountDetails.bank}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Директор',
            name: 'director',
            bind: {
                value: '{contractor.accountDetails.director}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Гл. Бухгалтер',
            name: 'chiefAccountant',
            bind: {
                value: '{contractor.accountDetails.chiefAccountant}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Р/С',
            name: 'account',
            bind: {
                value: '{contractor.accountDetails.account}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'К/С',
            name: 'correspondentAccount',
            bind: {
                value: '{contractor.accountDetails.correspondentAccount}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }
    ],

    convertRecord: function(record){
        return record.getAccountDetails();
    }
});