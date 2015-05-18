Ext.define("TransDocs.service.PermitActionService", {
    extend: 'Ext.Class',
    singleton: true,

    constructor: function(config){
        this.callParent(arguments);
    },

    isPermitAction: function(actionName, targetObject){
        var writer = Ext.create('TransDocs.data.writer.AssociationJsonWriter');
        var result = true;
        Ext.Ajax.request({
            async: false,
            url: "PermitActionController/is.permit.action",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            method: 'POST',
            timeout: 180000,
            jsonData: targetObject ? writer.getRecordData(targetObject) : 'null',

            params: {
                actionName: actionName
            },
            success: function(response, options){
                result = response.responseText;
            },
            failure: function(response, options){
                result = false;
            }
        });
        return result==='true';
    }
});