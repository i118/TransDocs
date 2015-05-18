Ext.define("TransDocs.controller.file.FileUploadController",{
    extend: 'Ext.app.ViewController',
    alias: 'controller.fileUploadController',

    fileUpload: function (button) {
        var fileUpload = this.lookupReference("fileUpload");
        var form = fileUpload.getForm();
        if (form.isValid()) {
            var id = !fileUpload.getContainerModel().isNew() ? fileUpload.getContainerModel().getId() : null;
            form.setValues({containerId: id});
            var fileModel = Ext.create(fileUpload.getModelClass());
            form.setValues({fileType: fileModel.get("objectType")});
            var me = this;
            form.submit({
                url: 'FileController/create.file',
                waitMsg: 'Загрузка вашего файла',
                success: function (fp, o) {
                    var resp = Ext.decode(o.response.responseText);
                    if (resp.results[0]) {
                        fileModel.set(resp.results[0]);
                        if (fileModel.get("fileType") === "FILE") {
                            fileModel.set('iconCls', TransDocs.service.FileService.getIcon(fileModel));
                        }
                        fileModel.set("leaf", fileModel.get("fileType") === "FILE");
                        fileUpload.getContainerModel().files().add(fileModel);
                        fileUpload.getContainerModel().appendChild(fileModel);
                    }
                    var window = me.getView();
                    window.close();
                    Ext.Msg.alert("", 'Файл ' + fileModel.get("name") + ' успешно загружен');

                }
            });
        }
    },

    fileUploadClose: function (button) {
        var fileUpload = this.getView();
        if (fileUpload) {
            fileUpload.close();
        }
    }
});