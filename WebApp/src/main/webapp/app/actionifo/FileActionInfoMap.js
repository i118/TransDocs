Ext.define("TransDocs.actionifo.FileActionInfoMap", {
    extend: 'Ext.Base',
    config: {
        model: undefined
    },
    requires: ["TransDocs.service.PermitActionService"],
    actions: {
        checkout: "CheckoutFile",
        checkIn: "CheckInFile",
        cancelEdit: "CancelEditFile",
        rename: "RenameFile",
        remove: "DeleteFile",
        view: "ViewFile",
        download: "DownloadFile"
    },

    constructor: function (config) {
        this.model = config.model;
        this.actionAccessMap = Ext.create("Ext.util.HashMap"),
            this.callParent(arguments);
        this.initActionInfoMap();
        this.initEvents();
    },

    initActionInfoMap: function () {
        if (!this.getModel())return;
        var me = this;
        var isFile = this.getModel().get("fileType") == "FILE";
        this.registerAction(this.actions.checkout, {available: me.isAvailable(me.actions.checkout), visible: true, action: this.actions.checkout });
        this.registerAction(this.actions.checkIn, {available: me.isAvailable(me.actions.checkIn), visible: true, action: this.actions.checkIn});
        this.registerAction(this.actions.cancelEdit, {available: me.isAvailable(me.actions.checkIn), visible: true, action: this.actions.cancelEdit});
        this.registerAction(this.actions.view, {available: isFile, visible: true, action: this.actions.view});
        this.registerAction(this.actions.remove, {available: me.isAvailable(me.actions.remove), visible: true, action: this.actions.remove});
        this.registerAction(this.actions.rename, {available: me.isAvailable(me.actions.rename), visible: false, action: this.actions.rename});
        this.registerAction(this.actions.download, {available: isFile, visible: false, action: this.actions.download});

    },

    initEvents: function () {
        var me = this;
        this.getModel().on({
            checkout: function () {
                this.reInitCheckIn();
                this.reInitChangeActions();
            },
            checkIn: function () {
                this.reInitCheckIn();
                this.reInitChangeActions();
            },
            cancelEditFile: function () {
                this.reInitCheckIn();
                this.reInitChangeActions();
            },
            scope: me
        });
    },

    registerAction: function (actionName, accessObject) {
        this.actionAccessMap.add(actionName, accessObject);
    },

    getActionInfo: function (actionName) {
        return this.actionAccessMap.get(actionName);
    },

    isAvailable: function (actionName) {
        return TransDocs.service.PermitActionService.isPermitAction(actionName, this.getModel())
    },

    getModel: function () {
        return this.model;
    },


    reInitChangeActions: function(){
        var remove = this.getActionInfo(this.actions.remove);
        var rename = this.getActionInfo(this.actions.rename);
        if(remove){
            remove.available = this.isAvailable(this.actions.remove);
            rename.available =remove.available;
        }
    },

    reInitCheckIn: function () {
        var checkIn = this.getActionInfo(this.actions.checkIn);
        var cancelCheckout = this.getActionInfo(this.actions.cancelEdit);
        if (checkIn) {
            checkIn.available = this.isAvailable(this.actions.checkIn);
            cancelCheckout.available = checkIn.available;
        }
    }
});