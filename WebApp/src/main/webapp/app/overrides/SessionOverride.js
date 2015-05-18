Ext.define("TransDocs.overrides.SessionOverride",{
    override: 'Ext.data.Session',

    privates: {
        evict: function(record) {
            var entityName = record.entityName,
                entry;

            entry = this.data[entityName];

            if (entry) {
                delete entry[record.getId()];
            }
        }
    },

    evictRecord: function(record){
        this.evict(record);
    }
});
