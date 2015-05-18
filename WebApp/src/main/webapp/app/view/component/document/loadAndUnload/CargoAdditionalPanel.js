/**
 * Created by zerotul on 16.04.15.
 */
Ext.define('TransDocs.view.component.document.loadAndUnload.CargoAdditionalPanel', {
    extend: 'TransDocs.view.component.AbstractForm',
    alias: 'widget.cargoAdditionalPanel',

    layout: {
        type: 'table',
        columns: 2,
        tableAttrs: {
            style: {
                width: '100%'
            }
        },tdAttrs:{
            style: {
                padding: "0 10 0 0"
            }
        },trAttrs:{
            style: {
                padding: "0 0 0 5"
            }
        }
    },

    margin: "10 10 0 10",
    defaults: {
        width: "100%",
        labelWidth: 130
    },

    items: [
        {
            xtype: 'textfield',
            fieldLabel: 'Сопровод. док-ты'
        },{
            xtype: 'textfield',
            fieldLabel: 'Требования к ТС'
        },{
            xtype: 'textfield',
            fieldLabel: 'Товарный код'
        },{
            xtype: 'textfield',
            fieldLabel: 'Требования зак-ва РФ'
        },{
            xtype: 'textfield',
            fieldLabel: 'Вид упаковки'
        },{
            xtype: 'textfield',
            fieldLabel: 'Сроки и темп. реж'
        },{
            xtype: 'textfield',
            fieldLabel: 'Размер упак.'
        },{
            xtype: 'textfield',
            fieldLabel: 'Запорно-пломб устр-ва'
        },{
            xtype: 'textfield',
            fieldLabel: 'Страхование'
        },{
            xtype: 'textfield',
            fieldLabel: 'Запрещение перегрузки'
        },{
            xtype: 'textfield',
            fieldLabel: 'Состояние груза'
        },{
            xtype: 'textfield',
            fieldLabel: 'Страна происхождения'
        }
    ]
});