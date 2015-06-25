Ext.define("TransDocs.view.component.dictionary.CarForm", {
    extend: "TransDocs.view.component.AbstractForm",
    alias: "widget.carForm",


    layout: {
        type: 'table',
        columns: 2
    },
    autoScroll: true,
    defaultType: 'textfield',
    method: 'post',
    overflowY: 'auto',
    config: {
        modelClass: null,
        contractor: null
    },

    listeners: {
        close: 'closeCarForm'
    },

    bbar: [
        '->', {
            xtype: 'button',
            text: 'Отменить',
            action: 'CloseDriverForm',
            handler: 'cancel'
        },
        {
            xtype: 'button',
            text: 'Сохранить',
            action: 'SaveCar',
            handler: 'saveCar',
            bind: {
                hidden: "{!isEditMode}"
            }
        }
    ],

    items: [
        {
            xtype: 'fieldset',
            title: 'Данные машины',
            height: 155,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Марка машины',
                    bind: {
                        value: '{car.carBrand}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Гос. номер',
                    bind: {
                        value: '{car.carNumber}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                }
            ]
        }, {
            xtype: 'fieldset',
            title: 'Данные прицепа',
            height: 155,
            items: [
                {
                    xtype: 'textfield',
                    fieldLabel: 'Марка Прицепа',
                    bind: {
                        value: '{car.trailerBrand}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Грузоподъемность',
                    bind: {
                        value: '{car.capacity}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },
                {
                    xtype: 'textfield',
                    fieldLabel: 'Гос. номер',
                    bind: {
                        value: '{car.trailerNumber}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                }, {
                    xtype: 'textfield',
                    fieldLabel: 'Кубатура',
                    bind: {
                        value:'{car.cubage}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                },  {
                    xtype: 'textfield',
                    fieldLabel: 'Тип прицепа',
                    bind: {
                        value: '{car.trailerType}',
                        editable: '{isEditMode}'
                    },
                    labelAlign: 'left',
                    flex: 1
                }


            ]
        }
    ]

});