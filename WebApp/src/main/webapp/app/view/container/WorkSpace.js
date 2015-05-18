Ext.define("TransDocs.view.container.WorkSpace",{
    extend: 'Ext.panel.Panel',
    alias: 'widget.workspace',

    requires:[
        'TransDocs.view.NavigationBar',
        'TransDocs.view.container.TaskBar'
    ],
    layout: 'border',
    items: [
        {
            xtype:'navigationBar',
            region: 'west'
        },{
            xtype: "panel",
            region: "center",
            id: "workSpaceCenter"
        }
    ],

    dockedItems: [
        {
            xtype: 'taskBar'
        }
    ],

    tbar: ['->',{
        xtype: 'button',
        text: 'Выход',
        handler:function(){
            window.location = "j_spring_security_logout"
        }
    }]

});
