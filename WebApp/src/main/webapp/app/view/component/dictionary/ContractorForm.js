Ext.define("TransDocs.view.component.dictionary.ContractorForm",{
    extend: 'TransDocs.view.component.AbstractForm',
    alias: 'widget.contractorform',
    defaultType: 'textfield',
    layout: 'anchor',
    border: false,
    store: null,
    items:[
        {
            fieldLabel: 'Полное наименование',
            name: 'fullName',
            bind: {
                value: '{contractor.fullName}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }, {
            fieldLabel: 'Краткое наименование',
            name: 'shortName',
            bind: {
                value:'{contractor.shortName}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }, {
            xtype: 'combobox',
            fieldLabel: 'Организационно-правовая форма',
            name: 'legalForm',
            bind: {
                value:'{contractor.legalForm}',
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
                        key: 'OJSC' ,name: 'ОАО'
                    },
                    {
                        key: 'LLC', name: 'ООО'
                    },
                    {
                        key: 'CJSC', name: 'ЗАО'
                    },
                    {
                        key: 'IB', name: 'ИП'
                    }
                ]
            }
        }, {
            fieldLabel: 'Юредический адрес',
            name: 'legalAddress',
            bind: {
                value: '{contractor.legalAddress}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }, {
            fieldLabel: 'Почтовый адрес',
            name: 'mailingAddress',
            bind: {
                value:'{contractor.mailingAddress}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }, {
            fieldLabel: 'Телефон',
            bind: {
                value:'{contractor.phone}',
                editable: '{isEditMode}'
            },
            name: 'phone',
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }, {
            fieldLabel: 'E-Mail',
            name: 'email',
            bind: {
                value: '{contractor.email}',
                editable: '{isEditMode}'
            },
            labelAlign: 'left',
            cls: 'field-margin',
            flex: 1
        }
    ],

    constructor: function(config){
      this.callParent(arguments);
    },

    getStore: function(){
        return this.store;
    },

    lookupStore: function(){
        return this.lookupViewModel().get("contractor").persons();
    }

});