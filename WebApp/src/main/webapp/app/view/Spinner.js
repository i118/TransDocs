Ext.define("TransDocs.view.Spinner", {
    extend: 'Ext.Base',

    config: {
        viewModel: null,
        view: null
    },

    binds: new Ext.util.Collection(),


    addBind: function (descriptor, opts) {
        var me = this;
        var conf = Ext.apply({bindTo: descriptor}, opts)
        if(this.getViewModel()) {
            var bind = this.getViewModel().bind(conf, function (val) {
                bind.destroy();
                me.binds.remove(bind);
                if(me.binds.count()==0){
                    me.hideMask();
                }
            });
            this.binds.add(bind);
        }
    },

    applyMask: function () {
        if(this.isLoading() && this.getViewModel()){
            var view = this.getMaskedView();
            view.setLoading(true, true);
        }
    },

    hideMask: function(){
        if(!this.isLoading() && this.getViewModel()){
            var view = this.getMaskedView();
            view.setLoading(false);
            this.destroy();
        }
    },

    isLoading: function () {
        if(this.destroyed) return false;
        return this.binds.count()>0;
    },

    destroy: function() {
        this.destroyed = true;
        for (var i =0; i < this.binds.count(); i++) {
            var bind = this.binds.get(i);
            if (bind) {
                bind.destroy();
            }
        }
        delete this.binds;
    },

    getMaskedView: function(){
        return this.getView() ? this.getView() : this.getViewModel().getView();
    }
});
