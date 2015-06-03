/**
 * Created by zerotul on 03.06.15.
 */
Ext.define('TransDocs.viewmodel.document.OrderDocumentViewModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.orderdocument',

    requires: [
        "TransDocs.data.store.dictionary.CustomerStore",
        "TransDocs.data.store.dictionary.UserStore",
        "TransDocs.data.store.dictionary.CarrierStore",
        "TransDocs.data.store.document.OrderDocumentStore",
        "TransDocs.data.store.dictionary.SimpleDictionaryStore",
        "TransDocs.data.store.dictionary.CompanyStore"
    ],
    formulas:{
        isSelectedCustomer: function (get) {
            return get("document").getCustomer() != null;
        },
        isSelectedCarrier: function (get) {
            return get("document").getCarrier()!= null;
        },

        customer: {
            bind: '{document.customer}',


            get: function (customer) {
                return customer;
            },
            set: function (newValue) {
                var me = this;
                var changeCustomer = function(customer){
                    var document = me.get("document");
                    if(customer) {
                        document.set("customerAddress", customer.get("legalAddress"));
                        document.set("customerPhone", customer.get("phone"));
                        document.set("customerEmail", customer.get("email"));
                    }
                    document.setCustomer(customer);
                    me.set("isSelectedCustomer", customer!=null);
                    me.set("customerPersons", customer.persons());
                    document.setCustomerPerson(null);
                }
                if(newValue && !newValue.isModel){
                    var rec = this.getSession().peekRecord("Customer", newValue);
                    if(!rec) {
                        this.getSession().getRecord("Customer", newValue, {
                            success: function (record) {
                                changeCustomer(record);
                            }
                        });
                    } else{
                        changeCustomer(this.getSession().getRecord("Customer", newValue));
                    }
                }else{
                    changeCustomer(newValue);
                }
            }
        },
        customerPersons: {
            bind: '{document.customer.persons}',
            get: function(persons){
                return persons;
            }
        },
        carrier: {
            bind: '{document.carrier}',

            get: function (carrier) {
                return carrier;
            },
            set: function (newValue) {
                var me = this;
                var changeCarrier = function(carrier){
                    var document = me.get("document");
                    if(carrier) {
                        document.set("carrierAddress", carrier.get("legalAddress"));
                        document.set("carrierPhone", carrier.get("phone"));
                        document.set("carrierEmail", carrier.get("email"));
                    }
                    document.setCarrier(carrier);
                    me.set("isSelectedCarrier", carrier!=null);
                    me.set("carrierPersons", carrier.persons());
                    document.setCarrierPerson(null);
                }
                if(newValue && !newValue.isModel){
                    var rec = this.getSession().peekRecord("Carrier", newValue);
                    if(!rec) {
                        this.getSession().getRecord("Carrier", newValue, {
                            success: function (record) {
                                changeCarrier(record);
                            }
                        });
                    }else{
                        changeCarrier(this.getSession().getRecord("Carrier", newValue));
                    }
                }else{
                    changeCarrier(newValue);
                }
            }
        },
        carrierPersons: {
            bind: '{document.carrier.persons}',
            get: function(persons){
                return persons;
            }
        }
    },
    stores: {
        customerStore: {
            type: 'customerStore'
        },
        userStore: {
            type: 'userStore'
        },
        carrierStore: {
            type: 'carrierStore'
        },
        orderTitleStore: {
            type: 'simpleDictionaryStore',
            session: true
        },
        paymentDateStore: {
            type: 'simpleDictionaryStore',
            session: true
        },
        transportTypeStore: {
            type: 'simpleDictionaryStore',
            session: true
        },
        companyStore: {
            type: 'companyStore',
            session: true
        }
    }
});