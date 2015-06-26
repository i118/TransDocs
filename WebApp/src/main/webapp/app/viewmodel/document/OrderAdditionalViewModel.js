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
                if(!additional){
                    additional = this.getSession().createRecord("OrderAdditional");
                    this.get("document").setOrderAdditional(additional);
                }
                return additional;
            }
        },

        driver: {
            bind: "{orderTransport.driver}",

            get: function(driver){
                return driver;
            },

            set: function(newValue){
                var me = this;
                var changeDriver = function(driver){
                    var transport = me.get("orderTransport");
                    if(transport) {
                        var car = driver.getCar();
                        if(car) {
                            transport.setCar(car);
                            transport.set("trailer", car.get("trailerBrand"));
                        }else{
                            transport.setCar(null);
                        }
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

        orderTransport: {
            bind: '{document.orderTransport}',

            get: function(orderTransport){
                if(!orderTransport){
                    orderTransport = this.getSession().createRecord("OrderTransport");
                    this.get("document").setOrderTransport(orderTransport);
                }
                return orderTransport;
            }
        },

        transportDrivers: {
            bind: '{document.carrier.drivers}',

            get: function(drivers){
                return drivers;
            }
        }
    },
    stores: {
        simpleDictionaryStore: {
            type: 'simpleDictionaryStore'
        }
    }
})