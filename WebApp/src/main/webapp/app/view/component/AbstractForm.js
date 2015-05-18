Ext.define('TransDocs.view.component.AbstractForm', {
    extend: 'Ext.form.Panel',
    method: 'post',
    alias: "widget.abstractForm",
    overflowY: 'auto',
    jsonSubmit: true,
    lazyLoad: true,
    config: {
        model: null
    },

    listeners: {
        activate: function () {
            var store = this.lookupStore();
            if (store && store.isLoading()) {
                this.setLoading(true, true);
            }
        }
    },

    defaults: {
        triggerWrapCls: "x-form-custom-trigger-wrap"
    },

    initComponent: function () {
        this.callParent(arguments);
        var me = this;
        var store = this.lookupStore();
        if (store) {
            store.on("load", function () {
                me.setLoading(false);
            });
        }
    },

    each: function (fn, scope) {
        var data = this.items,
            dLen = data.length;

        for (i = 0; i < dLen; i++) {
            var formItem = data.get(i);
            if (fn.call(scope || formItem, formItem, dLen) === false) {
                break;
            }
        }
    },

    loadRecord: function (record) {

        record = this.convertRecord(record);
        if (this.lazyLoad) {
            this.on('activate', function (panel, tab) {
                if (this.getRecord()) return;
                TransDocs.view.component.AbstractForm.superclass.loadRecord.call(this, record);
            }, this);
        } else {
            this.callParent(arguments);
        }
    },

    convertRecord: function (record) {
        return record;
    },

    lookupStore: function () {

    }
});