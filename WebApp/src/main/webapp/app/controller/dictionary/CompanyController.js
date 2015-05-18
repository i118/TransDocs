Ext.define('TransDocs.controller.dictionary.CompanyController', {
    extend: 'TransDocs.controller.dictionary.AbstractContractorController',
    alias: 'controller.companyController',
    requires: [
        "TransDocs.service.CompanyService",
        "TransDocs.view.component.dictionary.CompanyComponent",
        "TransDocs.data.store.dictionary.CompanyStore",
        "TransDocs.service.UserService",
        "TransDocs.view.component.dictionary.UserForm",
        "TransDocs.view.container.ChildWindow",
        "TransDocs.controller.dictionary.UserComponentController"

    ],

    createContractorInfoComponent: function(config){
        config.reference = "companyComponent";
        var viewModel = {
            session: config.session,
            data: {
                contractor: config.record,
                isEditMode: config.isEditMode
            },
            stores: {
                contractorStore: config.store
            }
        };
        config.viewModel = viewModel;
        return Ext.create('TransDocs.view.component.dictionary.CompanyComponent', config);
    },

    addUser: function(button){
        var parentWnd = button.up('window');
        var companyComponent =this.lookupContractorInfo();
        var session = companyComponent.getSession();
        var user = TransDocs.service.UserService.newUser(session);
        var viewModel = companyComponent.lookupViewModel();
        var company = viewModel.get("contractor");
        company.users().add(user);
        var viewModel =  {
            data:{
                user:user,
                isEditMode: viewModel.get("isEditMode"),
                isCreateMode: true
            }
        }
        var wnd = Ext.create("TransDocs.view.container.ChildWindow", {
            width: 350,
            autoHeight: true,
            parent: parentWnd,
            reference: 'userWindow',
            session: session.spawn(),
            controller: "userComponent",
            viewModel: viewModel,
            items: [
                {
                    xtype: 'panel',
                    layout: 'fit',
                    border:false,
                    items: [
                        {
                            xtype: 'userform',
                            reference: "userform"
                        }
                    ]
                }

            ]
        });
        wnd.show();
    },

    cancelUserForm: function(){
        var userForm= this.lookupReference("userform");
        if(userForm){
            var viewModel = userForm.lookupViewModel();
            var companyComponent =this.lookupContractorInfo();
            var companyViewModel = companyComponent.lookupViewModel();
            var user = viewModel.get("user");
            if(viewModel.get("isCreateMode")){
                companyViewModel.get("contractor").users().remove(user);
            }else{
                user.reject();
            }
          this.lookupReference("userWindow").close();
        }
    },

    saveUserForm: function(){
        var userWindow= this.lookupReference("userWindow");
        if(userWindow){
            userWindow.close();
        }
    },


    deleteUser: function(){

    },

    openUser: function(){

    },

    createNewContractor: function(session){
        return  TransDocs.service.CompanyService.newCompany(session);
    },

    lookupContractorInfo: function(){
        return this.lookupReference('companyComponent');
    },

    lookupContractorTree: function(){
        return this.lookupReference('companyTree');
    },

    lookupContractContainer: function(){
        return this.lookupReference('companyContainer');
    },

    lookupDictionaryPanel: function(){
        return this.lookupReference("companyPanel");
    },

    lookupStore: function(session){
        return Ext.create("TransDocs.data.store.dictionary.CompanyStore",{session: session});
    },

    lookupTreeStore: function(){
        return this.getViewModel().getStore("companyTreeStore");
    }
});