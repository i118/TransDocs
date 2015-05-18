package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by zerotul on 26.01.15.
 * Тип перевозки
 */
public enum TransportationType {
    /**
     * Междугородняя
     */
    INTERCITY,
    /**
     * Городская
     */
    CITY,
    /**
     * Пригородная
     */
    SUBURBAN,
    /**
     * Международная
     */
    INTERNATIONAL,
    /**
     * Пассажирская
     */
    PASSENGER;

    @JsonCreator
    public static  TransportationType forValue(String value){
        try {
            return TransportationType.valueOf(value);
        }catch (IllegalArgumentException e){
            return null;
        }
    }

    @JsonValue
    public String toValue(){
        return   this.toString();
    }
}
