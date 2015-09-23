Ext.define("TransDocs.model.AbstractModel", {
    extend: 'Ext.data.Model',
    idProperty: 'objectId',
    requires: [
        'Ext.data.identifier.Uuid',
        "TransDocs.data.reader.DefaultJsonReader",
        "TransDocs.data.writer.AssociationJsonWriter"
    ],
    identifier: 'uuid',
    fields: [
        {name: 'objectId', type: 'string'},
        {name: 'version', defaultValue: -1, critical: true},
        {name: 'creationDate', type: 'date'},
        {name: 'modifyDate', type: 'date'},
        {
            name: 'objectType', type: 'string', critical: true, persist: true, convert: function (newValue, model) {
            return model.getObjectType();
        }
        },
        {name: 'deleted', type: 'boolean'}
    ],

    actionMapEnabled: false,
    actionMap: undefined,


    schema: {
        proxy: {
            type: 'rest',
            timeout: 180000,
            appendId: true,
            idParam: "objectId",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            paramsAsJson: true,
            actionMethods: {
                create: 'POST',
                read: 'POST',
                update: 'PUT',
                destroy: 'DELETE'
            },
            api: {
                create: '{entityName}/create.object',
                update: '{entityName}/update.object',
                destroy: '{entityName}/delete.object',
                read: '{entityName}/get.object'
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
        }
    },

    getObjectType: function () {
        throw "unimplemented method, class = " + Ext.getClass(this).getName();
    },

    isNew: function () {
        return this.get('version') == -1;
    },

    reload: function (callBack, params, url) {

        var me = this;
        return Ext.getClass(this).load(this.getId(), {
            url: url ? url : this.url,
            params: params ? params : this.params,
            success: function (r, o) {
                var k;
                for (k in r.data) {
                    me.data[k] = r.data[k];
                }
                me.commit();
                if (Ext.isFunction(callBack)) {
                    callBack(true);
                }
            },
            failure: function () {
                if (Ext.isFunction(callBack)) {
                    callBack(false);
                }
            }
        });
    },

    refresh: function (store, callback) {
        var me = this;
        store.load({
            id: me.getId(),
            addRecords: true,
            callback: function (records, operation, success) {
                if (success && store.getSession() && records.length > 0) {
                    var record = records[0];
                    if (record.raw) {
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


    getActionMap: function () {
        if (!this.actionMap && this.actionMapEnabled) {
            this.actionMap = this.createActionMap();
        }
        return this.actionMap;
    },


    setDirty: function (flag) {
        this.dirty = flag;
    },

    isDirty: function () {
        return this.dirty;
    },

    createActionMap: function () {
    },

    getAssociatedData: function(result, options, dirtyGraph) {
        var me = this,
            associations = me.associations,
            deep, i, item, items, itemData, length, record, role, roleName, opts, clear, associated, dirty = {dirty:false};
        dirtyGraph = dirtyGraph ? dirtyGraph : {dirty:false};
        result = result || {};
        me.$gathering = 1;
        if (options) {
            options = Ext.Object.chain(options);
        }
        for (roleName in associations) {
            dirty = {dirty:false};
            role = associations[roleName];
            item = role.getAssociatedItem(me);
            if (!item || item.$gathering) {

                continue;
            }
            if (item.isStore) {
                item.$gathering = 1;
                items = item.getData().items;
                length = items.length;
                itemData = [];
                for (i = 0; i < length; ++i) {
                    record = items[i];
                    if(me.isNew() || record.isDirty() ){
                        dirty.dirty = true;
                    }
                    deep = !record.$gathering;
                    record.$gathering = 1;
                    if (options) {
                        associated = options.associated;
                        if (associated === undefined) {
                            options.associated = deep;
                            clear = true;
                        } else if (!deep) {
                            options.associated = false;
                            clear = true;
                        }
                        opts = options;
                    } else {
                        opts = deep ? me._getAssociatedOptions : me._getNotAssociatedOptions;
                    }
                    var deepDirty = dirty.dirty ? {dirty:false} :  dirty;
                    itemData.push(record.getData(opts, deepDirty));
                    if (clear) {
                        options.associated = associated;
                        clear = false;
                    }
                    delete record.$gathering;
                }
                delete item.$gathering;
            } else {
                opts = options || me._getAssociatedOptions;
                if (options && options.associated === undefined) {
                    opts.associated = true;
                }
                if(me.isNew() || item.isDirty() || me.isModified(role.association.field.getName())){
                    dirty.dirty = true;
                }
                var deepDirty = dirty.dirty ? {dirty:false} :  dirty;
                itemData = item.getData(opts, deepDirty);
            }
            if(dirty.dirty) {
                dirtyGraph.dirty = true;
                result[roleName] = itemData;
            }
        }
        delete me.$gathering;
        return result;
    },

    getData: function(options, dirtyGraph) {
        var me = this,
            ret = {},
            opts = (options === true) ? me._getAssociatedOptions : (options || ret),

            data = me.data,
            associated = opts.associated,
            changes = opts.changes && !me.phantom,
            critical = changes && opts.critical,
            content = changes ? me.modified : data,
            fieldsMap = me.fieldsMap,
            persist = opts.persist,
            serialize = opts.serialize,
            criticalFields, field, n, name, value;
        if (content) {
            for (name in content) {
                value = data[name];
                field = fieldsMap[name];
                if (field) {
                    if (persist && !field.persist) {
                        continue;
                    }
                    if (serialize && field.serialize) {
                        value = field.serialize(value, me);
                    }
                }
                ret[name] = value;
            }
        }
        if (critical) {
            criticalFields = me.self.criticalFields || me.getCriticalFields();
            for (n = criticalFields.length; n-- > 0; ) {
                name = (field = criticalFields[n]).name;
                if (!(name in ret)) {
                    value = data[name];
                    if (serialize && field.serialize) {
                        value = field.serialize(value, me);
                    }
                    ret[name] = value;
                }
            }
        }
        if (associated) {
            me.getAssociatedData(ret, opts, dirtyGraph);
        }
        return ret;
    },

    isDirty: function () {
        this.phantom = this.phantom && this.isNew();
        if(this.phantom)return true;
        var me = this;
        var fields = this.getFields();
        var isModified = false;
        for (var i = 0; i < fields.length; ++i) {
            var field = fields[i];
            if (me.isModified(field.getName())) {
                isModified = true;
                break;
            }
        }
        return isModified;
    }
});
