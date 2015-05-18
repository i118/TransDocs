Ext.define("TransDocs.view.component.AbstractComponent",{
    extend:"Ext.tab.Panel",

    config: {
        record: null,
        store: null
    },

    autoLoadRecord: true,

    autoBind: true,

    initComponent: function(){
        this.initStore();
        this.callParent(arguments);
        if(this.record && this.autoLoadRecord) {
            this.loadRecord(this.record);
        }
    },

    loadRecord: function(record){
        var me = this;
        if(!record.isNew()) {
            this.setLoading(true, true);
            this.getStore().load({
                id: record.getId(),
                single: true,
                callback: function (records, operation) {
                    if (records.length == 1) {
                        me.record = records[0];
                        for (var i = 0; i < me.items.length; i++) {
                            var item = me.items.getAt(i);
                            if (item) {
                                item.loadRecord(me.record);
                            }
                        }
                    }
                    me.setLoading(false);
                }
            });
        }else{
            this.record =record;
            this.setLoading(true, true);
            for (var i = 0; i < this.items.length; i++) {
                var item = this.items.getAt(i);
                if (item) {
                    item.loadRecord(this.record);
                }
            }
            this.setLoading(false);
        }
    },

    enable: function(){
        this.setDisabled(false);
    },

    disable: function(){
        this.setDisabled(true);
    },

    setDisabled: function(isDisable){
        for(var i=0; i< this.items.length; i++){
            var item = this.items.getAt(i);
            if(item){
                item.each(function (scope, formItem, dLen) {
                    scope.setDisabled(isDisable);
                });
            }
        }
    },

    bindRecord: function(){
        for(var i=0; i< this.items.length; i++){
            var item = this.items.getAt(i);
            if(item && item.getRecord()){
                item.updateRecord();
            }
        }
    },

    getRecord: function(autoBind){
        if(autoBind) {
            this.bindRecord();
        }
        return  this.record;
    },

    cancelEditModel: function(){
        this.getRecord().reject();
        this.getRecord().setDirty(false);
    },

    initStore: function(){

    },

    getStore: function(){
        return this.store;
    }
});