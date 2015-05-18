Ext.define('TransDocs.data.proxy.dictionary.UserTreeProxy', {
    extend: 'TransDocs.data.proxy.AbstractTreeProxy',
    alias: "proxy.userTreeProxy",

    config: {
        controllerName: 'UserController'
    },
    paramsAsJson: true,
    constructor: function () {
        this.callParent(arguments);
    }
});