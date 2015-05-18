Ext.define("TransDocs.model.file.CustomerFileModel", {
    extend: 'TransDocs.model.file.AbstractFileModel',

    fields: [
        {name: 'containerId', reference: {
            type: "TransDocs.model.file.CustomerFileModel",
            association: 'CustomerFileByContainer',
            role: 'container',
            inverse: 'files'
        }}
    ],

    getObjectType: function () {
        return "td_customer_file";
    }
});
