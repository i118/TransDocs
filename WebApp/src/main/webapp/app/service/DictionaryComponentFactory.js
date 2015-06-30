Ext.define("TransDocs.service.DictionaryComponentFactory", {
    extend: 'Ext.Class',
    singleton: true,
    builders: Ext.create("Ext.util.HashMap"),

    requires:[
        "TransDocs.view.container.dictionary.UserDictionaryPanel",
        "TransDocs.controller.dictionary.UserComponentController",
        "TransDocs.controller.dictionary.CustomerComponentController",
        "TransDocs.view.container.dictionary.CustomerDictionaryPanel",
        "TransDocs.view.container.dictionary.CarrierDictionaryPanel",
        "TransDocs.controller.dictionary.CarrierController",
        "TransDocs.viewmodel.dictionary.UserTreeViewModel",
        "TransDocs.viewmodel.dictionary.CustomerTreeViewModel",
        "TransDocs.viewmodel.dictionary.CarrierTreeViewModel",
        "TransDocs.viewmodel.dictionary.SimpleDictionaryViewModel",
        "TransDocs.view.component.SimpleDictionaryPanel",
        "TransDocs.controller.dictionary.SimpleDictionaryController"
    ],

    constructor: function(config){
        this.callParent(arguments);
        this.builders.add("user_dictionary", this.userDictionaryComponentBuilder);
        this.builders.add("customer_dictionary", this.customerDictionaryBuilder);
        this.builders.add("carrier_dictionary", this.carrierDictionaryBuilder);
        this.builders.add("transport_type", this.getSimpleDictionaryComponentBuilder);
        this.builders.add("order_title", this.getSimpleDictionaryComponentBuilder);
        this.builders.add("payment_date", this.getSimpleDictionaryComponentBuilder);
        this.builders.add("border_crossing", this.getSimpleDictionaryComponentBuilder);
        this.builders.add("additional_service", this.getSimpleDictionaryComponentBuilder);
    },

    getDictionaryComponent: function (dictionary, customConfig) {
        var builder = this.builders.get(dictionary.get("dictionaryType").trim().toLowerCase());
        if(!builder){
            Ext.Msg.show({
                title: 'Справочники',
                msg: 'Компонент для справочника "'+dictionary.get("description")+'" не найден',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.INFO
            });
            return;
        }
        return builder.call(this, dictionary, customConfig);
    },


    getSimpleDictionaryComponentBuilder: function(dictionary, customConfig){
        var wndConf = {
            viewModel: {
                type: 'simpleDictionaryViewModel',
                parent: null,
                data:{
                    dictionaryType: dictionary.get("dictionaryType"),
                    restrictions:{
                        dictionaryType: {
                            propertyName: "dictionaryType",
                            value: dictionary.get("dictionaryType"),
                            operator: "="
                        }
                    }
                }
            },
            title: dictionary.get("description"),
            width: 450, height: 250, controller: "simpleDictionaryController",
            scope: dictionary.get("objectId"), items: [
                {
                    xtype: 'simpleDictionary',
                    reference:"simpleDictionary"
                }
            ], layout: 'fit'
        };
        if(customConfig) {
            Ext.apply(wndConf, customConfig)
        }
        return wndConf;
    },

    userDictionaryComponentBuilder: function (dictionary, customConfig) {
        var wndConf = {
            title: dictionary.get("description"),
            viewModel: {
                type: "userTree",
                parent: null
            },
            width: 620, height: 500, controller: "userComponent",
            scope: dictionary.get("objectId"), items: [
                {
                    xtype: 'userDictionaryPanel',
                    reference:"userDictionaryPanel"
                }
            ], layout: 'fit'
        };
        if(customConfig) {
            Ext.apply(wndConf, customConfig)
        }
        return wndConf;
    },

    customerDictionaryBuilder: function(dictionary, customConfig){
        var wndConf = {title: dictionary.get("description"),
            width: 620, height: 500, controller: 'customerComponentController',
            viewModel: {
                type: "customerTree",
                parent: null
            },
            scope: dictionary.get("objectId"), items: [
                {
                    xtype: "customerDictionaryPanel",
                    reference: "customerDictionaryPanel"
                }
            ], layout: 'fit'
        };
        return wndConf;
    },

    carrierDictionaryBuilder: function(dictionary, customConfig) {
        var wndConf = {
            title: dictionary.get("description"),
            width: 620, height: 500, controller: 'carrierController',
            viewModel: {
                type: "carrierTree",
                parent: null
            },
            scope: dictionary.get("objectId"), items: [
                {
                    xtype: "carrierDictionaryPanel",
                    reference: "carrierDictionaryPanel"
                }
            ], layout: 'fit'
        };
        return wndConf;
    }
});