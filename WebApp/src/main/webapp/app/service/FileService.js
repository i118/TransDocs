Ext.define("TransDocs.service.FileService", {
    extend: 'Ext.Class',
    singleton: true,
    iconMap: Ext.create("Ext.util.HashMap"),
    fileTransfer: undefined,

    requires: [
        "TransDocs.view.component.file.RenameFileComponent"

    ],

    constructor: function () {
        this.callParent(arguments);
        this.initIconMap();
    },

    getIcon: function (file) {
        var extension = file.get("extension");
        var icon;
        if (extension) {
            icon = this.iconMap.get(extension);
            if (icon) {
                return icon;
            }
        }
        icon = this.iconMap.get("default");
        return icon;
    },

    initIconMap: function () {
        this.iconMap.add("xls", "tree-icon-excel");
        this.iconMap.add("xlsx", "tree-icon-excel");
        this.iconMap.add("doc", "tree-icon-word");
        this.iconMap.add("docx", "tree-icon-word");
        this.iconMap.add("docx", "tree-icon-word");
        this.iconMap.add("zip", "tree-icon-archive");
        this.iconMap.add("rar", "tree-icon-archive");
        this.iconMap.add("xml", "tree-icon-xml");
        this.iconMap.add("png", "tree-icon-image");
        this.iconMap.add("jpg", "tree-icon-image");
        this.iconMap.add("jpeg", "tree-icon-image");
        this.iconMap.add("tiff", "tree-icon-image");
        this.iconMap.add("txt", "tree-icon-txt");
        this.iconMap.add("default", "tree-icon-file");
    },

    checkoutFile: function (fileModel, callback) {
        if(fileModel.get("fileType")!="FILE")return;
        var waitMsg = this.showProgress('Окрытие файла, пожалуйста подождите...', 'Открытие...');
        try {
            if (!this.fileTransfer) {
                this.fileTransfer = this.buildFileTransfer();
            }
            if (fileModel.get("fileType") != "FILE") return;
            var result = this.fileTransfer.checkoutFile({
                fileId: fileModel.getId(),
                callback:function(result){
                    try {
                        fileModel.reload(function () {
                            fileModel.reloadCallBack();
                            if(callback) {
                                callback.call();
                            }
                            fileModel.fireEvent("checkout", fileModel);
                        }, null, "FileController/load.file");

                    }finally{
                        waitMsg.close();
                    }
                }
            });
        }catch(e){
            waitMsg.close();
            this.showMessage("На вашем компьюьтере не установлен java plugin, либо он заблокирован системой безопасности.", Ext.MessageBox.ERROR);
        }
        return result;
    },

    viewFile: function (fileModel, callback) {
        if(fileModel.get("fileType")!="FILE")return;
        var waitMsg = this.showProgress('Окрытие файла, пожалуйста подождите...', 'Открытие...');
        try {
            if (!this.fileTransfer) {
                this.fileTransfer = this.buildFileTransfer();
            }
            if (fileModel.get("fileType") != "FILE") return;
            var result = this.fileTransfer.viewFile({
                fileId: fileModel.getId(),
                callback:function(result){
                    try {
                        fileModel.reload(function () {
                            fileModel.reloadCallBack();
                            if(callback) {
                                callback.call();
                            }
                            fileModel.fireEvent("viewFile", fileModel);
                        }, null, "FileController/load.file");

                    }finally{
                        waitMsg.close();
                    }
                }
            });
        }catch(e){
            waitMsg.close();
            this.showMessage("На вашем компьюьтере не установлен java plugin, либо он заблокирован системой безопасности.", Ext.MessageBox.ERROR);
        }
        return result;
    },

    downloadFile: function(fileModel){
        if(fileModel.get("fileType")!="FILE")return;
        var fileUrl = applicationContext.getBaseUrl()+"/FileController/get.content.file/"+fileModel.getId();;
        var fileFrame = document.getElementById("downloadFileFrame");
        if(fileFrame){
            fileFrame.src = fileUrl;
        }else{
            Ext.log({level: "warn"}, "downloadFileFrame not found")
        }
    },

    cancelEditFile: function (fileModel, callback) {
        if(fileModel.get("fileType")!="FILE")return;
        var waitMsg = this.showProgress('Отмена редактирования, пожалуйста подождите...', 'Отмена...');
        try {
            if (!this.fileTransfer) {
                this.fileTransfer = this.buildFileTransfer();
            }
            if (fileModel.get("fileType") != "FILE") return;
            var result = this.fileTransfer.cancelEdit({
                fileId: fileModel.getId(),
                callback:function(result){
                    try {
                        fileModel.reload(function () {
                            fileModel.reloadCallBack();
                            if(callback) {
                                callback.call();
                            }
                            fileModel.fireEvent("cancelEditFile", fileModel);
                        }, null, "FileController/load.file");

                    }finally{
                        waitMsg.close();
                    }
                }
            });
        }catch(e){
            waitMsg.close();
            this.showMessage("На вашем компьюьтере не установлен java plugin, либо он заблокирован системой безопасности.", Ext.MessageBox.ERROR);
        }
        return result;
    },

    checkInFile: function (fileModel, callback) {
        if(fileModel.get("fileType")!="FILE")return;
        var waitMsg = this.showProgress("Запись файла, пожалуйста подождите...", "Запись...");
        var me =  this;
        try {
            if (!this.fileTransfer) {
                this.fileTransfer = this.buildFileTransfer();
            }
            if (fileModel.get("fileType") != "FILE") return;
            var result = this.fileTransfer.checkInFile({
                fileId: fileModel.getId(),
                callback:function(result){
                    try {
                        fileModel.reload(function () {
                            fileModel.reloadCallBack();
                            if(callback) {
                                callback.call();
                            }
                            fileModel.fireEvent("checkIn", fileModel);
                        }, null, "FileController/load.file");

                        if(result == "COMPLETE"){
                            me.showMessage("Файл "+fileModel.get("name")+" успешно записан!", Ext.MessageBox.INFO);
                        }else{
                            me.showMessage("При записи файла "+fileModel.get("name")+" произошла ошибка!", Ext.MessageBox.ERROR);
                        }
                    }finally{
                        waitMsg.close();
                    }
                }
            });
        }catch(e){
            waitMsg.close();
            this.showMessage("На вашем компьюьтере не установлен java plugin, либо он заблокирован системой безопасности.", Ext.MessageBox.ERROR);
        }

        return result;
    },

    renameFile: function(fileModel, parentComponent){
        var wnd = Ext.create("TransDocs.view.container.ChildWindow",{
            layout: "fit",
            parent: parentComponent,
            createItems: function(){
                var renameComponent = Ext.widget("renameFileComponent",{file: fileModel});
                this.items= [
                    renameComponent
                ];
               this.renameFileComponent = renameComponent;
            },

            loadRecord: function(fileModel){
                var name = fileModel.get("name");
                var index = name.lastIndexOf(".");
                if(index!=-1){
                    name = name.substring(0, index);
                }
                this.renameFileComponent.getForm().setValues({name: name});
            },

            updateRecord: function(){
               var newName = this.renameFileComponent.getForm().getValue("name");
               newName = newName+"."+fileModel.get("extension");
               fileModel.set("name",newName);
            }
        });
        wnd.loadRecord(fileModel);
        wnd.show();
    },

    removeFile: function(fileModel, container){
        if(fileModel) {
            fileModel.drop();
            fileModel.save();
        }
    },


    buildFileTransfer: function(){
        var applet = document.getElementById("fileTransfer");
        return applet;
    },

    showProgress: function(msg, waitText){
        var waitMsg = Ext.MessageBox.show({
            wait: true,
            msg: msg,
            width: 400,
            waitConfig: {interval: 50, animate: true, text: waitText}
        });
        return waitMsg;
    },

    showMessage: function(text, msgType){
        Ext.MessageBox.show({
            msg: text,
            icon: msgType,
            buttons: Ext.Msg.OK
        });
    }
});
