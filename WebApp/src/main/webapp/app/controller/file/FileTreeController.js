Ext.define("TransDocs.controller.file.FileTreeController", {
    extend: 'Ext.app.ViewController',
    alias: 'controller.fileTreeController',

    requires: [
        'TransDocs.view.container.file.FileUploadWindow',
        'TransDocs.view.container.file.FolderWindow',
        "TransDocs.service.FileService"
    ],

    addFile: function (button) {
        var parentWnd = button.up('window');
        var fileList = this.getView();

        var fileContainer = this.getFileContainer(fileList);

        var wnd = Ext.create("TransDocs.view.container.file.FileUploadWindow", {
            width: "400",
            height: "150",
            parent: parentWnd,
            containerModel: fileContainer,
            reference: 'fileUploadWindow',
            modelClass: fileList.getModelClass()
        });
        wnd.show();
    },


    showContextMenu: function(view, record, item, index, e){
        var tree = this.getView(),
            menu = tree.menu;
        if (!menu) {
            menu = tree.menu = tree.add({
                xtype: "menu",
                margin: '0 0 10 0',
                items: [
                    {
                        text: 'Промотр',
                        action: 'ViewFile',
                        handler: 'openFile'
                    },
                    {
                        text: 'Редактировать',
                        action: 'CheckoutFile',
                        handler: 'сheckOutFile'
                    },
                    {
                        text: 'Скачать',
                        action: 'DownloadFile',
                        handler: 'downloadFile'
                    },
                    {
                        text: 'Сохранить',
                        action: 'CheckInFile',
                        handler: 'checkInFile'
                    },
                    {
                        text: 'Отменить редактирование',
                        action: 'CancelEditFile',
                        handler: 'cancelEditFile'
                    },
                    {
                        text: 'Переименовать',
                        action: 'RenameFile',
                        handler: 'renameFile'
                    },
                    {
                        text: 'Удалить',
                        action: 'DeleteFile',
                        handler: 'deleteFile'
                    }
                ],
                reference: 'contextmenu'

            });
            tree.menu.on({
                beforeshow: {fn: 'beforeShowMenu', scope: this}
            });
        }
        e.stopEvent();
        menu.showBy(item, 'tl-tl', [e.getX() - view.getX(), 9]);
    },

    beforeShowMenu: function(menu){
        var me = this.getView();
        var fileModel = me.getSelectedFile();
        if (!fileModel) {
            for (var i = 0; i < menu.items.length; i++) {
                var item = menu.items.get(i);
                if (item) {
                    item.disable();
                }
            }
        } else {
            for (var i = 0; i < menu.items.length; i++) {
                var item = menu.items.get(i);
                if (item) {
                    var action = fileModel.getActionMap() ? fileModel.getActionMap().getActionInfo(item.action) : undefined;
                    if (action) {
                        if (action.available) {
                            item.enable();
                        } else {
                            item.disable();
                        }
                    } else {
                        item.enable();
                    }
                }
            }
        }
    },

    addFolder: function (button) {
        var parentWnd = button.up('window');
        var fileList = this.getView();
        var fileContainer = this.getFileContainer(fileList);
        var folder = Ext.create(fileList.getModelClass());
        folder.set("fileType", "FOLDER");
        folder.set("leaf", false);
        var folderWindow = Ext.create("TransDocs.view.container.file.FolderWindow", {
            width: "400",
            height: "150",
            containerModel: fileContainer,
            parent: parentWnd,
            reference: 'folderWindow',
            modelClass: fileList.getModelClass()
        });
        folderWindow.loadRecord(folder);
        folderWindow.show();
    },


    openFile: function (grid, record) {
        var file =  this.getView().getSelectedFile();
        if (file && file.get('fileType')=="FILE") {
            TransDocs.service.FileService.viewFile(file);
        }
    },

    сheckOutFile: function(grid, record){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.checkoutFile(file);
        }
    },

    checkInFile: function(view){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.checkInFile(file);
        }
    },

    downloadFile: function(){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.downloadFile(file);
        }
    },

    cancelEditFile: function(){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.cancelEditFile(file);
        }
    },

    renameFile: function(){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.renameFile(file, this.getView().up("window"));
        }
    },

    deleteFile: function(){
        var file = this.getView().getSelectedFile();
        if (file) {
            TransDocs.service.FileService.removeFile(file, this.getView().getModel());
        }
    },

    getFileContainer: function (list) {
        var selectionNode = list.getSelectionModel().getSelection();
        var fileContainer;
        if (selectionNode && selectionNode.length == 1 && selectionNode[0].get("fileType") != "FILE") {
            fileContainer = selectionNode[0];
        } else {
            fileContainer = list.lookupViewModel().get("fileStore");
        }
        return fileContainer;
    }
});