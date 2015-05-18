package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by zerotul on 24.02.15.
 * Способы олаты
 * CASHLESS = Безналичный расчет
 * SPOTCASH = Наличный расчет
 */
public enum PaymentMethod {
    CASHLESS, SPOTCASH;

    @JsonCreator
    public static  PaymentMethod forValue(String value){
        try {
            return PaymentMethod.valueOf(value);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @JsonValue
    public String toValue(){
        return   this.toString();
    }
}
