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
                value:'{contractor.inn}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'КПП',
            name: 'kpp',
            bind: {
                value: '{contractor.kpp}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'БИК',
            name: 'bic',
            bind: {
                value: '{contractor.bic}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОКПО',
            name: 'okpo',
            bind: {
                value: '{contractor.okpo}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОГРН',
            name: 'ogrn',
            bind: {
                value: '{contractor.ogrn}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'ОКВЭД',
            name: 'okved',
            bind: {
                value: '{contractor.okved}',
                editable: '{isEditMode}',
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Банк',
            name: 'bank',
            bind: {
                value: '{contractor.bank}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Директор',
            name: 'director',
            bind: {
                value: '{contractor.director}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Гл. Бухгалтер',
            name: 'chiefAccountant',
            bind: {
                value: '{contractor.chiefAccountant}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'Р/С',
            name: 'account',
            bind: {
                value: '{contractor.account}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        },{
            fieldLabel: 'К/С',
            name: 'correspondentAccount',
            bind: {
                value: '{contractor.correspondentAccount}',
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