Ext.define("TransDocs.service.AdminComponentFactory", {
    extend: 'Ext.Class',
    singleton: true,
    builders: Ext.create("Ext.util.HashMap"),

    requires:[
        "TransDocs.view.container.admin.CompanyPanel",
        "TransDocs.viewmodel.dictionary.CompanyTreeViewModel",
        "TransDocs.controller.dictionary.CompanyController"
    ],

    constructor: function(config){
        this.callParent(arguments);
        this.builders.add("company", this.companyComponentBuilder);
    },

    getComponent: function (record, customConfig) {
        var builder = this.builders.get(record.get("sectionType"));
        if(!builder){
            Ext.Msg.show({
                title: 'Справочники',
                msg: 'Компонент для справочника "'+record.get("description")+'" не найден',
                buttons: Ext.Msg.OK,
                icon: Ext.Msg.INFO
            });
            return;
        }
        return builder.call(this, record, customConfig);
    },

    companyComponentBuilder: function (section, customConfig) {
        var wndConf = {
            title: section.get("description"),
            viewModel: {
                type: "companyTree"
            },
            width: 620, height: 500, controller: "companyController",
            scope: section.get("objectId"), items: [
                {
                    xtype: 'companyPanel',
                    reference:"companyPanel"
                }
            ], layout: 'fit'
        };
        if(customConfig) {
            Ext.apply(wndConf, customConfig)
        }
        return wndConf;
    }
});