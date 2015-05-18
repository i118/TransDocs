Ext.define("TransDocs.view.container.file.FolderWindow", {
    extend: "TransDocs.view.container.ChildWindow",
    alias: "widget.folderWindow",
    layout: "fit",
    controller: 'folderComponentController',
    parent: undefined,
    folderComponent: undefined,
    config:{
        containerModel: undefined,
        modelClass: null
    },

    requires:[
        "TransDocs.view.component.file.FolderComponent",
        'TransDocs.controller.file.FolderComponentController'
    ],

    createItems: function(){
        var me = this;
        var folderComponent = Ext.widget("folderComponent",{containerModel: me.containerModel, reference: 'folderComponent', modelClass: me.getModelClass()});
        this.items= [
            folderComponent
        ];
        this.folderComponent = folderComponent;
    },

    loadRecord: function(record){
        this.folderComponent.loadRecord(record);
    },

    updateRecord: function(){
        this.folderComponent.updateRecord();
    },

    getRecord: function(){
      return this.folderComponent.getRecord();
    }
});