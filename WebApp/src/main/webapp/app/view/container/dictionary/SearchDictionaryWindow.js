Ext.define("TransDocs.view.container.dictionary.SearchDictionaryWindow",{
    extend: "TransDocs.view.container.ChildWindow",

    viewModel: {
        data:{
            isEditMode: false
        }
    },

    selectHandler: null,
    scope: null,
    caller: null,

    constructor: function(config){
        var me = this;
        this.bbar = [
            "->",
            {
                text: "Отмена",
                handler:  function(){
                    config.closeHandler ? config.closeHandler() : me.close();
                }
            },{
                text: "Выбрать",
                handler: me.select
            }
        ];

        this.callParent(arguments);
    },

    select: function(button){
        var wnd = button.up("window");
        var controller = this.lookupController();
        if(!controller)throw "controller not found";
        if(typeof(controller.getSelected) != "function")throw "unimplemented method \"getSelected\" in controller " + Ext.getClass(controller).getName();;
        var selected = controller.getSelected()
        var selections;
        if(selected && wnd.caller) {
            if ( wnd.caller.multiSelect) {
                selections = [selected];
            }else{
                selections = selected;
            }
        }
        wnd.selectHandler.call(wnd.scope, wnd.caller, selections);
        wnd.close();
    }
});