Ext.define("TransDocs.model.dictionary.CarrierModel",{
    extend: 'TransDocs.model.dictionary.ContractorModel',
    requires: [
        'TransDocs.model.dictionary.AccountDetailsInterface',
        'TransDocs.model.file.CarrierFileModel',
        "TransDocs.data.reader.DefaultJsonReader",
        "TransDocs.data.writer.AssociationJsonWriter"
    ],

    fields: [
        {name: "files", persist:false},
        {name: "fileStoreId", reference: 'TransDocs.model.file.CarrierFileModel', unique:true}
    ],


    proxy: {
        type: 'rest',
        timeout: 180000,
        appendId: true,
        idParam: "objectId",
        api:{
            create: 'CarrierController/create.object',
            update: 'CarrierController/update.object',
            destroy: 'CarrierController/delete.object',
            read:  'CarrierController/get.object'
        },
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        listeners: {
            exception: function (proxy, response, operation) {
                Ext.MessageBox.show({
                    title: 'Error!',
                    msg: operation.getError() ? operation.getError() : "Ошибка связи с сервером",
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK,
                    resizable: true,
                    overflowY: 'auto',
                    overflowX: 'auto'
                });
            }
        },
        reader: {
            type: "defaultjson"
        },
        writer: {
            type: "associationJsonWriter"
        }
    },


    files: function(){
        return this.getFileStore().files();
    },

    getObjectType: function(){
        return  'td_carrier';
    }
});