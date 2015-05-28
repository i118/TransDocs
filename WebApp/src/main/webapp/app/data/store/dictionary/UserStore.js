Ext.define("TransDocs.data.store.dictionary.UserStore",{
    extend: 'TransDocs.data.store.AbstractFormStore',

    requires: [
        "TransDocs.model.dictionary.UserModel"
    ],
    alias: 'store.userStore',
    model: 'TransDocs.model.dictionary.UserModel'
});