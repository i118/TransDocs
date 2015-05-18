Ext.define("TransDocs.view.component.dictionary.ContractPersonPanel", {
    extend:"TransDocs.view.component.AbstractForm",
    alias: "widget.contractPersonPanel",
    layout: "fit",
    requires:[
        'TransDocs.view.grid.dictionary.ContractPersonListView'
    ],

    config:{
        modelClass: null
    },

    controller: "contractPersonController",
    isLoaded: false,

    initComponent: function(config){
        this.createItems();
        this.callParent(arguments);
    },

    createItems: function(){
        var me = this;
        this.items= [
            {
                xtype: 'panel',
                layout: 'fit',
                listeners:{
                    disable: function(comp, eOpts){
                        for(var i=0; i< comp.items.length; i++){
                            var item = comp.items.getAt(i);
                            if(item){
                                item.setDisabled(true);
                            }
                        }
                    },

                    enable: function(comp, eOpts){
                        for(var i=0; i< comp.items.length; i++){
                            var item = comp.items.getAt(i);
                            if(item){
                                item.setDisabled(false);
                            }
                        }
                    }
                },
                items:[{
                    xtype: 'panel',
                    layout:'fit',
                    items: [
                        {
                            xtype: 'contactPersonListView',
                            contractor: me.model,
                            modelClass: me.getModelClass(),
                            reference: "contactPersonListView"
                        }
                    ]
                }]
            }
        ]
    },

    loadRecord: function(data){
        this.model = data;
        var personList = this.down("contactPersonListView");
        if(personList && !this.isLoaded){
            this.on('activate',function(){
                personList.loadRecord(this.model);
                this.isLoaded=true;
            }, this);
        }
    },

    lookupStore: function () {
       return  this.lookupViewModel().get("contractor").persons();
    }
});