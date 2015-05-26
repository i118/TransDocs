Ext.define("TransDocs.view.grid.file.FileListView", {
    extend: 'Ext.tree.Panel',
    alias: 'widget.fileListView',
    autoWidth: true,
    autoHeight: true,
    frame: true,
    isLoaded: false,
    config: {
        model: null,
        modelClass: null
    },
    controller: 'fileTreeController',

    bind:{
        store: '{fileTreeStore}'
    },

    requires: [
        'TransDocs.data.store.file.FileTreeStore',
        'TransDocs.view.component.TDButton',
        'Ext.menu.Menu',
        'TransDocs.controller.file.FileTreeController'
    ],

    selModel: {
        type: "rowmodel",
        singleSelect: true
    },

    menuItems: undefined,

    columns: [
        {
            xtype: 'treecolumn',
            text: 'Наименование',
            dataIndex: 'name',
            flex: 1
        }
    ],

    tbar: [
        {
            xtype: 'button',
            text: 'Добавить файл',
            action: 'addFile',
            handler: 'addFile',
            bind: {
                disabled: "{!isEditMode}"
            }
        },
        {
            xtype: 'button',
            text: 'Добавить папку',
            action: 'addFolder',
            handler: 'addFolder',
            bind: {
                disabled: "{!isEditMode}"
            }
        }
        ,
        {
            text: 'Операции',
            name: 'fileActions',
            menu: {
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
                listeners: {
                    beforeshow: 'beforeShowMenu'
                }
            }
        }
    ],

    listeners: {
        itemcontextmenu: 'showContextMenu',
        itemdblclick: 'openFile'
    },


    getSelectedFile: function () {
        var selModel = this.getSelectionModel();
        var selection = selModel.getSelection();
        if (selection && selection.length > 0) {
            return selection[0];
        }
        return undefined;
    }
});