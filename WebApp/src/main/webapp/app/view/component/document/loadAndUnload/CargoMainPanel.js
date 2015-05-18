/**
 * Created by zerotul on 16.04.15.
 */
Ext.define('TransDocs.view.component.document.loadAndUnload.CargoMainPanel', {
    extend: 'TransDocs.view.component.AbstractForm',
    alias: 'widget.cargoMainPanel',

    layout: {
        type: 'table',
        columns: 3,
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
        labelWidth: 105
    },

    items: [
        {
            xtype: 'textfield',
            fieldLabel: 'Наименование',
            colspan: 3
        },{
            xtype: 'textfield',
            fieldLabel: 'Масса брутто (кг)'
        },{
            xtype: 'textfield',
            fieldLabel: 'Масса нетто (кг)',
            labelStyle: 'margin-left: 10px;'
        },{
            xtype: 'textfield',
            fieldLabel: 'Габариты (м.)'
        },{
            xtype: 'textfield',
            fieldLabel: 'Объем (м3)'
        },{
            xtype: 'textfield',
            fieldLabel: 'Кол-во мест'
        },{
            xtype: 'textfield',
            fieldLabel: 'Ед. изм'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Вид тары'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Способ упаковки'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Маркировка'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Опасность груза',
            colspan: 3
        }, {
            xtype: 'textfield',
            fieldLabel: 'Состояние упаковки'
        }, {
            xtype: 'textfield',
            fieldLabel: 'Стоимость груза'
        }, {
            xtype: 'datefield',
            fieldLabel: 'Дата готовности'
        }
    ]
});