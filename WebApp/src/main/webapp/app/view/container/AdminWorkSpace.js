Ext.define("TransDocs.view.container.AdminWorkSpace",{
    extend: 'Ext.panel.Panel',
    alias: 'widget.adminworkspace',

    requires:[
        'TransDocs.view.AdminNavigationBar',
        'TransDocs.view.container.TaskBar'
    ],
    layout: 'border',
    items: [
        {
            xtype:'adminNavigationBar',
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
