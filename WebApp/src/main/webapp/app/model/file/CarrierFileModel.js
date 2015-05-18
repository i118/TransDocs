Ext.define("TransDocs.model.file.CarrierFileModel", {
    extend: 'TransDocs.model.file.AbstractFileModel',

    fields: [
        {name: 'containerId', reference: {
            type: "TransDocs.model.file.CarrierFileModel",
            association: 'CarrierFileByContainer',
            role: 'container',
            inverse: 'files'
        }}
    ],

    getObjectType: function () {
        return "td_carrier_file";
    }
});
