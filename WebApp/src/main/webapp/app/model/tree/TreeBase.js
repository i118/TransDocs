Ext.define("TransDocs.model.tree.TreeBase", {
        extend: 'TransDocs.model.AbstractModel',
        requires: [
            'Ext.data.NodeInterface'
        ],

        mixins: [
            'Ext.mixin.Queryable'
        ],

        getRefItems: function () {
            return this.childNodes;
        },

        getRefOwner: function () {
            return this.parentNode;
        }
    },
    function () {
        Ext.data.NodeInterface.decorate(this);
    }
);