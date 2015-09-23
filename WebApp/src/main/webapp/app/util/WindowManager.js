Ext.define('TransDocs.util.WindowManager', {
    extend: 'Ext.Base',
    singleton: true,
    windows: Ext.create("Ext.util.HashMap"),
    taskBar: undefined,
    activeWindow: null,
    requires: [
        'TransDocs.view.container.Window'
    ],

    constructor: function (config) {
        this.callParent(arguments);
    },

    openWindow: function (config) {
        if (!config) return;

        var id = config.scope;
        if (!this.windows.get(id)) {
            var wnd = this.createWindow(config);

        } else {
            if (this.windows.get(id).isHidden()) {
                this.windows.get(id).show();
            } else {
                this.windows.get(id).focus();
            }
        }
    },

    createWindow: function (config) {
        config.closeAction = 'destroy';
        var items = config.items;
        config.items = [];
        var type = config.type ? config.type : 'TransDocs.view.container.Window';
        var wnd = Ext.create(type, config);
        this.windows.add(config.scope, wnd);
        var viewport = Ext.getCmp("TransDocsViewPort");
        var worSpace = Ext.getCmp("workSpaceCenter");
        worSpace.add(wnd);
        wnd.show();
        wnd.setLoading(true, true);
        for(var i=0; i<  items.length; i++){
            wnd.add(items[i]);
        }
        wnd.setLoading(false);
        return wnd;
    },


    getTaskBar: function () {
        if (!this.taskBar) {
            this.taskBar =  Ext.getCmp("TransDocsViewPort").down('taskBar');
        }
        return this.taskBar;
    },

    removeWin: function (wnd) {
        if (wnd.getTaskButton()) {
            TransDocs.util.WindowManager.getTaskBar().removeTaskButton(wnd.getTaskButton(), true);
        }
        TransDocs.util.WindowManager.getWindows().removeAtKey(wnd.scope);
        wnd.destroy();
    },

    markActive: function (wnd) {
        var currentActiveWindow = TransDocs.util.WindowManager.getActiveWindow();
        if (currentActiveWindow && currentActiveWindow != wnd) {
            TransDocs.util.WindowManager.markInactive(currentActiveWindow);
        }

        if (!wnd.getTaskButton()) {
            this.getTaskBar().createTaskButton(wnd);
        }

        TransDocs.util.WindowManager.setActiveWindow(wnd);
        TransDocs.util.WindowManager.getTaskBar().markActiveButton(wnd.getTaskButton());
        wnd.minimized = false;
    },

    markInactive: function (wnd) {
        var activeWindow = TransDocs.util.WindowManager.getActiveWindow();
        if (activeWindow && wnd && wnd == activeWindow) {
            TransDocs.util.WindowManager.setActiveWindow(null);
            TransDocs.util.WindowManager.getTaskBar().markInactiveButton()
        }
    },

    minimizeWin: function (wnd) {
        wnd.minimized = true;
        wnd.hide();
    },

    getWindow: function (id) {
        return this.windows.get(id);
    },

    getWindows: function () {
        return this.windows;
    },

    getActiveWindow: function () {
        return this.activeWindow;
    },

    setActiveWindow: function (wnd) {
        this.activeWindow = wnd;
    }


});