Ext.define("TransDocs.view.container.Window",{
    extend: "Ext.window.Window",
    minimizable: true,
    maximizable: true,
    taskButton: null,
    constrain: true,
 constructor: function(config){
        this.callParent(arguments);
        this.initConfig();
    },
    
    initComponent: function(){
        this.initEvent();
        this.callParent(arguments);
    },

    initEvent: function () {
        this.on('close', this.closeWin, this);
        this.on('minimize', this.minimizeWin, this);
        this.on('activate', this.markActive, this);
        this.on('focus', this.markActive, this);
        this.on('beforeshow', this.markActive, this);
        this.on('deactivate', this.markInactive, this);
    },

    focus: function(){
      this.callParent(arguments);
      this.fireEvent('focus');
    },

    getTaskButton: function(){
     return this.taskButton;
    },

    setTaskButton: function(button){
        this.taskButton = button;
    },

    closeWin: function(){
        TransDocs.util.WindowManager.removeWin(this);
    },

    minimizeWin: function(){
       TransDocs.util.WindowManager.minimizeWin(this);
    },

    markActive: function(){
        TransDocs.util.WindowManager.markActive(this);
    },

    markInactive: function(){
        TransDocs.util.WindowManager.markInactive(this);
    }
});