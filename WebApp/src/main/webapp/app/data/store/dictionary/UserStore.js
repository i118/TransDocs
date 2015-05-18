Ext.define("TransDocs.data.store.dictionary.UserStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',
    config:{
        controllerName: "UserController"
    },
    alias: 'store.userStore',
    model: 'TransDocs.model.dictionary.UserModel'
});