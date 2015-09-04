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

    getAssociatedData: function (result, operation, writer) {
        var me = this,
            associations = me.associations,
            deep, item, items, itemData, length, record, role, roleName;

        var getDataInternal = function (record) {
            var dataInternal;
            if (writer) {
                dataInternal = writer.getRecordData(record, operation);
                if (writer.getExpandData()) {
                    dataInternal = writer.getExpandedData([dataInternal])[0]
                }
            } else {
                dataInternal = record.getData(true);
            }
            return dataInternal;
        }
        result = result || {};

        me.$gathering = 1;

        for (roleName in associations) {
            role = associations[roleName];
            item = role.getAssociatedItem(me);
            if (!item || item.$gathering) {
                //if(role.isMany){
                //    result[roleName]=[];
                //}else{
                //    result[roleName]=null;
                //}
                continue;
            }

            if (item.isStore) {
                item.$gathering = 1;

                items = item.getData().items;
                length = items.length;
                itemData = [];
                var isManyToMany = role.association.isManyToMany;
                var isModified = isManyToMany || me.isModified(role.association.field.getName());
                if (!isModified) {
                    for (var i = 0; i < length; ++i) {
                        record = items[i];
                        if (record.isDirty()) {
                            isModified = true;
                            break;
                        }
                    }
                }
                if (!isModified) {
                    delete item.$gathering;
                    continue;
                }
                for (var i = 0; i < length; ++i) {
                    record = items[i];
                    deep = !record.$gathering;
                    record.$gathering = 1;
                    var data = getDataInternal(record);
                    itemData.push(data);
                    delete record.$gathering;
                }

                delete item.$gathering;
            } else {
                if (!item.isDirty() && !me.isModified(role.association.field.getName())) {
                    continue;
                }
                itemData = getDataInternal(item);
            }

            result[roleName] = itemData;
        }

        delete me.$gathering;

        return result;
    },

    isDirty: function () {
        this.phantom = this.isNew();
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
        return isModified || me.isDirtyAssociations();
    },

    isDirtyAssociations: function () {
        var me = this,
            associations = me.associations,
            roleName, role, item, items, length, record, deep;
        me.$associationDyrtyGathering = 1;
        for (roleName in associations) {
            role = associations[roleName];
            item = role.getAssociatedItem(me);
            if (!item || item.$associationDyrtyGathering) {
                continue;
            }
            if (item.isStore) {
                item.$associationDyrtyGathering = 1;

                items = item.getData().items;
                length = items.length;
                var isManyToMany = role.association.isManyToMany;
                var isModified = isManyToMany || me.isModified(role.association.field.getName());
                if (!isModified) {
                    for (var i = 0; i < length; ++i) {
                        record = items[i];
                        record.$associationDyrtyGathering = 1;
                        if (record.isDirty()) {
                            delete record.$associationDyrtyGathering;
                            return true;
                        }
                    }
                }

                delete item.$associationDyrtyGathering;
            } else {
                return me.isModified(role.association.field.getName()) || item.isDirty();
            }
        }
    }
});
