Ext.define("TransDocs.model.AbstractModel",{
    extend: 'Ext.data.Model',
    idProperty: 'objectId',
    requires: [
        'Ext.data.identifier.Uuid'
    ],
    identifier: 'uuid',
    fields: [
        {name: 'objectId', type: 'string'},
        {name: 'version', defaultValue: -1},
        {name: 'creationDate',type:'date'},
        {name: 'modifyDate',type:'date'},
        {name: 'objectType',type:'string', persist:true,  convert: function(newValue, model){
            return model.getObjectType();
        } },
        {name: 'deleted',type:'boolean'}
    ],

    actionMapEnabled: false,
    actionMap: undefined,

    getObjectType: function(){
        throw "unimplemented method, class = "+Ext.getClass(this).getName();
    },

    isNew: function(){
        return this.get('version')==-1;
    },

    reload: function(callBack, params, url) {

        var me = this;
        return Ext.getClass(this).load(this.getId(), {
            url: url ? url : this.url,
            params: params ? params : this.params,
            success: function(r, o) {
                var k;
                for (k in r.data) {
                    me.data[k] = r.data[k];
                }
                me.commit();
                if (Ext.isFunction(callBack)) {
                    callBack(true);
                }
            },
            failure: function() {
                if (Ext.isFunction(callBack)) {
                    callBack(false);
                }
            }
        });
    },

    refresh: function(store, callback){
        var me = this;
        store.load({
            id: me.getId(),
            addRecords: true,
            callback: function(records, operation, success){
                if(success && store.getSession() && records.length>0){
                    var record = records[0];
                    if(record.raw){
                        var rawData = store.getProxy().getReader().extractData(record.raw);
                        record.data = rawData[0].data;
                    }
                }
                if (Ext.isFunction(callback)) {
                    callback(records, operation, success);
                }
            }
        })
    },


    getActionMap: function(){
        if(!this.actionMap && this.actionMapEnabled){
            this.actionMap = this.createActionMap();
        }
        return this.actionMap;
    },


    setDirty: function(flag){
        this.dirty = flag;
    },

    isDirty: function(){
        return this.dirty;
    },

    createActionMap: function(){},

    getAssociatedData: function (result) {
        var me = this,
            associations = me.associations,
            deep, i, item, items, itemData, length, record, role, roleName;

        result = result || {};

        me.$gathering = 1;

        for (roleName in associations) {
            role = associations[roleName];
            item = role.getAssociatedItem(me);
            if (!item || item.$gathering) {
                if(role.isMany){
                    result[roleName]=[];
                }else{
                    result[roleName]=null;
                }
                continue;
            }

            if (item.isStore) {
                item.$gathering = 1;

                items = item.getData().items;
                length = items.length;
                itemData = [];

                for (i = 0; i < length; ++i) {
                    record = items[i];
                    deep = !record.$gathering;
                    record.$gathering = 1;
                    itemData.push(record.getData(true));
                    delete record.$gathering;
                }

                delete item.$gathering;
            } else {
                itemData = item.getData(true);
            }

            result[roleName] = itemData;
        }

        delete me.$gathering;

        return result;
    }
});
