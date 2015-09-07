Ext.define('TransDocs.data.writer.AssociationJsonWriter', {
    extend : 'TransDocs.data.writer.DefaultJsonWriter',
    alias: "writer.associationJsonWriter",
    getRecordData: function(record, operation) {
        var me = this, i, association, childStore, childRecord, data = {};
        data = me.callParent([record]);
        var associationData = record.getAssociatedData(data, {
            changes: true,
            critical: true,
            serialize: true
        });
        return data;
    }


});