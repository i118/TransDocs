Ext.define("TransDocs.controller.dictionary.CarFormController", {
    extend: 'Ext.app.ViewController',
    alias: 'controller.carFormController',

    closeCarForm: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        if(!viewModel.get("saveMode")) {
            var form = this.lookupReference("carForm");
            var car = viewModel.get("car");

            if (!car.isNew()) {
                car.reject();
            } else if (viewModel.get("isCreateMode")) {
                var contractor = car.getCarrier();
                contractor.cars().remove(car);
            }
        }
    },

    cancel: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", false);
        view.close();
    },

    saveCar: function(){
        var view = this.getView();
        var viewModel = view.lookupViewModel();
        viewModel.set("saveMode", true);
        var car = viewModel.get("car");
        car.set("description",  car.get("carBrand")+", "+car.get("carNumber"));
        view.close();
    }
});
