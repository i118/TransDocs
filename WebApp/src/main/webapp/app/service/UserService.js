Ext.define("TransDocs.service.UserService",{
    singleton: true,
    requires: [
        "TransDocs.data.store.dictionary.UserStore",
        'TransDocs.model.dictionary.UserModel'
    ],

    currentUser: null,

    constructor: function(){
        this.callParent(arguments);
        var userStore = Ext.create("TransDocs.data.store.dictionary.UserStore");
        var me = this;
        userStore.load({
            id: 'current',
            callback:function(records, operation, success){
                if(success) {
                    me.currentUser = records[0];
                }else{
                    throw "current user not loaded";
                }
            }
        })
    },

    getCurrentUser: function(){
      if(!this.currentUser){
          throw "current user not found";
      }
        return this.currentUser;
    },

    newUser: function(session){
        var user = session.createRecord('TransDocs.model.dictionary.UserModel');
        user.roleModels().blockLoadCounter=1;
        return user;
    }
});
