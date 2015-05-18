Ext.define("TransDocs.view.component.document.loadAndUnload.AdditionalCargoPanel",{
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.additionalCargoPanel",
    layout: {
        type: 'table',
        columns: 2,
        tableAttrs: {
            style: {
                width: '100%'
            }
        }
    },
    margin: "10 0 0 10",
    defaults: {
        labelWidth: 135,
        width: "95%"
    },
    items: [
        {
            xtype: 'textfield',
            fieldLabel: 'Сопровод. док-ты'
        },{
            xtype: 'textfield',
            fieldLabel: "Груз. счит. утрачен"
        },{
            xtype: 'textfield',
            fieldLabel: 'Спец. разреш'
        },{
            xtype: 'textfield',
            fieldLabel: 'Режим труда и отд'
        },{
            xtype: 'textfield',
            fieldLabel: 'Уведомлен. об эксп'
        },{
            xtype: 'textfield',
            fieldLabel: 'Контейнер'
        },{
            xtype: 'textfield',
            fieldLabel: 'Условия пог. раз. р-т'
        },{
            xtype: 'textfield',
            fieldLabel: 'Убытие на погрузку'
        },{
            xtype: 'textfield',
            fieldLabel: 'Условия хран. груза'
        },{
            xtype: 'textfield',
            fieldLabel: 'Возврат с выгрузки'
        },{
            xtype: 'textfield',
            fieldLabel: 'Общ. масса и сп. опр'
        },{
            xtype: 'textfield',
            fieldLabel: 'Факт. дата уб. с пог'
        },{
            xtype: 'textfield',
            fieldLabel: 'Свединия об опломб'
        },{
            xtype: 'textfield',
            fieldLabel: 'Факт. сост. конт.(погр)'
        },{
            xtype: 'textfield',
            fieldLabel: 'Подгот. ТС, пор. п/р'
        },{
            xtype: 'textfield',
            fieldLabel: 'Ф-я дата приб. на раз'
        },{
            xtype: 'textfield',
            fieldLabel: 'Штраф перевозчика'
        },{
            xtype: 'textfield',
            fieldLabel: 'Факт. сост. конт.(раз)'
        },{
            xtype: 'textfield',
            fieldLabel: 'Штраф грузоотправителя'
        },{
            xtype: 'textfield',
            fieldLabel: 'Время польз. ТС'
        }

    ]
});