Ext.define('TransDocs.model.dictionary.UserModel', {
    extend: 'TransDocs.model.AbstractModel',
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
            type: 'TransDocs.model.dictionary.CompanyModel',
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

    //TODO будет время надо будет переделать
    proxy: {
        type: 'rest',
        api: {
            read: 'UserController/find.byCompany'
        },
        reader: {type: 'defaultjson'},
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        listeners: {
            exception: function (proxy, response, operation) {
                Ext.MessageBox.show({
                    title: 'Error!',
                    msg: operation.getError() ? operation.getError() : "Ошибка связи с сервером",
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK,
                    resizable: true,
                    overflowY: 'auto',
                    overflowX: 'auto'
                });
            }
        }
    },


    getObjectType: function(){
        return "td_user";
    }

});