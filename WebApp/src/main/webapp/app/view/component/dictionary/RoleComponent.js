Ext.define('TransDocs.view.component.dictionary.RoleComponent',{
    extend: 'Ext.window.Window',
    alias: "widget.rolecomponent",
    layout:'fit',
    width: 400,
    height: 400,
    constrain: true,
    title:"Роли",
    config:{
        parent: undefined
    },

    draggable: false,
    modal:true,
    requires:[
        "TransDocs.view.grid.dictionary.RoleListView"
    ],
    items:[
        {
            xtype: 'rolelistview',
            reference: "rolelistview"
        }
    ],
    bbar: ['->',
        {
            xtype: 'button',
            text: 'Отменить',
            action: 'closeRoleComponent',
            handler: 'closeRoleComponent'
        },
        {
            xtype: 'button',
            text: 'Добавить',
            action: 'addRoleToUser',
            handler: 'addRoleToUser'
        }
    ],

    initComponent: function(){
      var me = this;
      if(this.parent){
          this.constrainTo = this.parent.getEl();
          this.on('beforeclose', function(){
              me.parent.enable();
          }, this);
          this.on('beforeshow', function(){
              me.parent.disable();
              me.enable();
          }, this);
          this.parent.add(this);
      }
      this.callParent(arguments);
    }

});