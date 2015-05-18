Ext.define("TransDocs.view.component.dictionary.ContractorContainer", {
    extend: "Ext.panel.Panel",
    layout: 'fit',
    alias: 'widget.contractorContainer',

    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',
        bind:{
            hidden: '{!isEditMode}'
        },
        items: [
            {
                xtype: 'button',
                text: "Добавить",
                action: "AddContractorAction",
                handler: "addContractor"
            }, {
                xtype: 'button',
                text: "Редактировать",
                action: "EditContractorAction",
                disabled: true,
                handler: "editContractor"
            }, {
                xtype: 'button',
                text: "Удалить",
                action: "DeleteContractorAction",
                disabled: true,
                handler: "deleteContractor"
            }, {
                xtype: 'button',
                text: "Востановить",
                action: "RestoreContractorAction",
                hidden: true,
                handler: "restoreContractor"
            },"-",
            {
                xtype: 'checkboxgroup',

                layout: 'hbox',
                padding:5,
                items: [
                    {
                        xtype: 'checkbox',
                        boxLabel: 'Удаленные объекты',
                        labelAlign: 'right',
                        action: 'ShowDeletedContractors',
                        handler: "showDeletedDictionary",
                        reference: "ShowDeletedContractors"
                    }
                ]
            }
        ]
    }],


    changeButtonState: function(selected){
        var editButton = this.down("button[action=EditContractorAction]");
        var deleteButton = this.down("button[action=DeleteContractorAction]");
        var restoreButton = this.down("button[action=RestoreContractorAction]");
        if(selected[0] && !selected[0].isRoot()){
            editButton.enable();
            if(selected[0].get('deleted')){
                restoreButton.enable();
                deleteButton.hide();
                restoreButton.show();
            } else{
                deleteButton.enable();
                restoreButton.hide();
                deleteButton.show();
            }
        }else {
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