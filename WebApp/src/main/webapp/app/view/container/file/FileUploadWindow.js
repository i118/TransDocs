Ext.define("TransDocs.view.container.file.FileUploadWindow", {
    extend: "TransDocs.view.container.ChildWindow",
    alias: "widget.fileUploadWindow",
    layout: "fit",

    parent: undefined,

    config:{
        containerModel: undefined,
        modelClass: null
    },

    controller: 'fileUploadController',

    requires:[
        "TransDocs.view.component.file.FileUploadComponent",
        "TransDocs.controller.file.FileUploadController"
    ],

    createItems: function(){
        var me = this;
        this.items= [
            {
                xtype: "fileUpload",
                containerModel: me.containerModel,
                reference: 'fileUpload',
                modelClass: me.getModelClass()
            }
        ];
    }

});
