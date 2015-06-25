Ext.define('TransDocs.model.dictionary.UserModel', {
    extend: 'TransDocs.model.AbstractModel',
    entityName: "User",
    fields:[
        {name: 'mail', type: 'string'},
        {name: 'phone', type: 'string'},
        {name: 'login', type: 'string'},
        {name: 'gender', type: 'string'},
        {name: 'patronymic', type: 'string'},
        {name: 'lastName', type: 'string'},
        {name: 'firstName', type: 'string'},
        {name: 'description', type: 'string'},
        {
            name: 'companyId', reference: {
            type: 'Company',
            association: 'CompanyModelByUserModel',
            role: 'company',
            inverse: 'users'
        }
        },
        {name: 'leaf', type:"boolean", convert: function(value, record){
            return record.get("objectId")!=record.getObjectType();
        }, persist:false}
    ],

    requires: [
        'TransDocs.model.dictionary.RoleModel',
        "TransDocs.model.dictionary.CompanyModel",
        'TransDocs.data.reader.DefaultJsonReader'
    ],

    manyToMany: {
        roleModels: {
            type: 'TransDocs.model.dictionary.RoleModel',
            role: 'roleModels',
            field: 'objectId'
        }
    },


    getObjectType: function(){
        return "td_user";
    }

});