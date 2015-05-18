Ext.define('TransDocs.model.dictionary.RoleModel', {
    extend: 'TransDocs.model.AbstractModel',
    fields:[
        {name: 'roleName', type: 'string'},
        {name: 'description', type: 'string'}
    ],

    getObjectType: function(){
        return "td_role";
    }
});
