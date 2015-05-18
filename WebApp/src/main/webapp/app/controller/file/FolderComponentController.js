Ext.define("TransDocs.controller.file.FolderComponentController",{
    extend: 'Ext.app.ViewController',
    alias: 'controller.folderComponentController',



    folderClose: function (button) {
        var folderWindow = this.getView();
        if (folderWindow) {
            folderWindow.close();
        }
    },


    saveFolder: function (button) {
        var folderComponent = this.lookupReference("folderComponent");
        var form = folderComponent.getForm();
        if (form.isValid()) {
            folderComponent.getForm().updateRecord()
            var record = folderComponent.getForm().getRecord();
            var fileContainer = folderComponent.getContainerModel();
            fileContainer.files().add(record);
            fileContainer.appendChild(record);
            var folderWindow = this.getView();
            folderWindow.close();
        }
    }
});