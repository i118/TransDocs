Ext.define("TransDocs.view.container.TaskBar", {
    extend: 'Ext.toolbar.Toolbar',
    alias: 'widget.taskBar',
//    id: 'TransDocsTaskBar',
    windows: [],
    activeButton: null,
    mixins: {
        observable: 'Ext.util.Observable'
    },

    constructor: function () {
        this.mixins.observable.constructor.call(this);
        this.dock = 'bottom';
        this.height = 30;
        this.callParent(arguments);
    },

    createTaskButton: function(wnd){
        var button = Ext.create("Ext.Button", {text: wnd.title, width: 80, tooltip:wnd.title,  overCls: ""});
        wnd.setTaskButton(button);
        button.taskWindow = wnd
        button.on('click', this.activateWindow, button);
        button.addCls('x-btn-default-toolbar-small-over');
        this.add(button);
    },

    activateWindow: function(){
        if(this.taskWindow.isHidden()){
            this.taskWindow.show();
        }else{
            this.taskWindow.focus();
        }
        TransDocs.util.WindowManager.getTaskBar().markActiveButton(this);
    },

    removeTaskButton: function(button){
        if(button == this.activeButton){
            this.activeButton=null;
        }
        this.remove(button, true);
    },

    markActiveButton: function(button){
        if(this.activeButton && button && button!=this.activeButton){
            this.markInactiveButton(button);
        }

        if(!button.hasCls('x-btn-default-toolbar-small-over')){
            button.addCls('x-btn-default-toolbar-small-over');
        }
        this.activeButton = button;
    },

    markInactiveButton: function(){
        if(this.activeButton){
            this.activeButton.removeCls('x-btn-default-toolbar-small-over');
        }
    }
});