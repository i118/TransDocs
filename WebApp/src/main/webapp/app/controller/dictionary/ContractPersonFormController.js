Ext.define("TransDocs.controller.dictionary.ContractPersonFormController",{
    extend: 'Ext.app.ViewController',
    alias: 'controller.contractPersonFormController',

    cancelContractPerson: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        if(!viewModel.get("saveMode")) {
            var isCreateMode = view.lookupViewModel().get('isCreateMode')
            var person = viewModel.get("record");
            var contractor = person.getContractor();
            if (!person.isNew()) {
                person.reject();
            } else if (isCreateMode) {
                contractor.persons().remove(person);
            }

        }
    },

    cancel: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", false);
        view.close();
    },

    saveContractPerson: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", true);
        view.close();
    }
});