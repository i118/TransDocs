Ext.define("TransDocs.data.store.admin.CompanyTreeStore",{
    extend: "TransDocs.data.store.AbstractTreeStore",
    alias: "store.companyTree",
    rootVisible:true,

    requires: [
        "TransDocs.model.dictionary.CompanyModel",
        "TransDocs.data.proxy.dictionary.CompanyTreeProxy"
    ],

    model: "TransDocs.model.dictionary.CompanyModel",

    proxy:{
      type: "companyTree"
    },

    root: {
        description: "Компании",
        expanded: false,
        objectId: "td_company"
    }
});