Ext.define('TransDocs.viewmodel.document.OrderAdditionalViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.orderAdditional',

    requires: [
        "TransDocs.model.document.OrderAdditional",
        "TransDocs.data.store.dictionary.SimpleDictionaryStore",
        "TransDocs.model.document.OrderTransport"
    ],

    formulas:{
        orderAdditional :{
            bind: '{document.orderAdditional}',

            get: function(additional){
                return additional;
            }
        },

        driver: {
            bind: "{orderTransport.driverId}",

            get: function(driver){
                return driver;
            },

            set: function(newValue){
                var me = this;
                var changeDriver = function(driver){
                    var transport = me.get("orderTransport");
                    if(driver) {
                        transport.setDriver(driver);
                        transport.set("driverPhone",driver.get("phone"));
                        var car = driver.getCar();
                        me.set("car", car)
                        var driverPassport = transport.getDriverPassport();
                        if(!driverPassport){
                            driverPassport = me.getSession().createRecord("TransDocs.model.Passport");
                            transport.setDriverPassport(driverPassport);
                        }

                        if(driver.getPassport()) {
                            driverPassport.set("serial", driver.getPassport().get("serial"));
                            driverPassport.set("number", driver.getPassport().get("number"));
                            driverPassport.set("issuedPassport", driver.getPassport().get("issuedPassport"));
                        }
                    }else{
                        transport.setDriver(driver);
                    }
                }
                if(newValue && !newValue.isModel){
                    var rec = this.getSession().peekRecord("Driver", newValue);
                    if(!rec) {
                        this.getSession().getRecord("Driver", newValue, {
                            success: function (record) {
                                changeDriver(record);
                            }
                        });
                    } else{
                        changeDriver(this.getSession().getRecord("Driver", newValue));
                    }
                }else{
                    changeDriver(newValue);
                }
            }
        },

        car: {
            bind: "{orderTransport.carId}",

            get: function(car){
                return car;
            },

            set: function(newValue){
                var me = this;
                var changeCar = function(car){
                    var transport = me.get("orderTransport");
                    transport.setCar(car);
                    if(car) {
                        transport.set("trailer", car.get("trailerBrand"));
                    }
                }
                if(newValue && !newValue.isModel){
                    var rec = this.getSession().peekRecord("Car", newValue);
                    if(!rec) {
                        this.getSession().getRecord("Car", newValue, {
                            success: function (record) {
                                changeCar(record);
                            }
                        });
                    } else{
                        changeCar(this.getSession().getRecord("Car", newValue));
                    }
                }else{
                    changeCar(newValue);
                }
            }
        },


        orderTransport: {
            bind: '{document.orderTransport}',

            get: function(orderTransport){
                return orderTransport;
            }
        },

        transportDrivers: {
            bind: '{document.carrier.drivers}',

            get: function(drivers){
                return drivers;

            }
        }, transportCars: {
            bind: '{document.carrier.cars}',

            get: function(cars){
                return cars;

            }
        }
    },
    stores: {
        simpleDictionaryStore: {
            type: 'simpleDictionaryStore'
        }
    }
})