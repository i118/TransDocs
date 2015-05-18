Ext.define('TransDocs.data.store.dictionary.UserTreeStore', {
    extend: "TransDocs.data.store.AbstractTreeStore",

    alias: 'store.userTreeStore',

    requires: [
        "TransDocs.data.proxy.dictionary.UserTreeProxy",
        "TransDocs.model.dictionary.UserModel"
    ],

    model: "TransDocs.model.dictionary.UserModel",
    rootVisible:true,
    proxy: {
        type: "userTreeProxy"
    },

    sorters: [{
        property: 'description',
        direction: 'ASC'
    }],

    root: {
        description: "Сотрудники",
        expanded: false,
        objectId: "td_user"
    }
});