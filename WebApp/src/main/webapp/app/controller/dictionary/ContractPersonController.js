Ext.define("TransDocs.controller.dictionary.ContractPersonController",{
    extend: 'Ext.app.ViewController',
    alias: 'controller.contractPersonController',

    requires: [
        "TransDocs.view.container.dictionary.ContractPersonWindow",
        "TransDocs.service.ContractorService"
    ],

    addPerson: function (button) {
        var parentWnd = button.up('window');
        var personPanel =this.getView();
        var modelClass = personPanel.getModelClass();
        if(!modelClass) throw "model class not determined"
        var session = personPanel.lookupSession();
        var person = session.createRecord(modelClass);
        var contractor = personPanel.lookupViewModel().get("contractor");
        if(typeof(contractor.persons) !='function')throw "unimplemented method persons: model = " + Ext.getClass(contractor).getName();
        contractor.persons().add(person);
        var viewModel = personPanel.lookupViewModel();
        TransDocs.service.ContractorService.openPerson(person, parentWnd, session.spawn(), viewModel.get("isEditMode"), true);
    },

    openPerson: function (grid, record) {
        var parentWnd = grid.up('window');
        var personPanel =this.getView();
        var session = personPanel.lookupSession();
        var viewModel = personPanel.lookupViewModel();
        TransDocs.service.ContractorService.openPerson(record, parentWnd, session.spawn(), viewModel.get("isEditMode"));
    },

    deletePerson: function (button) {
        var personList = this.lookupReference("contactPersonListView");
        var selection = personList.getSelectionModel().getSelection();
        if (selection && selection.length > 0) {
            var record = personList.lookupViewModel().get("contractor");
            for (var index in selection) {
                if(!selection[index].isNew()) {
                    selection[index].set("deleted", true);
                }else{
                    record.persons().remove(selection[index]);
                }
            }
        }
        personList.getView().refresh();
    }
});