Ext.define("TransDocs.view.container.dictionary.ContractPersonWindow",{
    extend: "TransDocs.view.container.ChildWindow",
    createMode: false,
    bind: {
        title: '{record.description}'
    },

    listeners:{
        close: 'cancelContractPerson'
    },

    requires: [
        "TransDocs.view.component.dictionary.ContractPersonForm",
        "TransDocs.controller.dictionary.ContractPersonFormController"
    ],

    controller: 'contractPersonFormController',

    items: [
        {
            xtype: 'contractPersonForm',
            reference: 'contractPersonForm'
        }
    ]
});