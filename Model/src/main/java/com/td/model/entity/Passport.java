package com.td.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by konstantinchipunov on 27.11.14.
 */
@Embeddable
@Access(AccessType.FIELD)
@JsonTypeName(Passport.JSON_TYPE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Passport implements Serializable, Cloneable {

    private static final long serialVersionUID = 6487002503082764790L;

    public static final String JSON_TYPE_NAME = "passport";

    @JsonProperty
    @Column(name = Columns.SERIAL)
    private String serial;

    @JsonProperty
    @Column(name = Columns.NUMBER)
    private String number;

    @JsonProperty
    @Column(name = Columns.ISSUED_PASSPORT)
    private String issuedPassport;

    public static class Columns{
         public static final String SERIAL = "passport_serial";
         public static final String NUMBER = "passport_number";
         public static final String ISSUED_PASSPORT = "issued_passport";
    }

    public Passport(String serial, String number, String issuedPassport) {
        this.serial = serial;
        this.number = number;
        this.issuedPassport = issuedPassport;
    }

    public Passport() {
    }

    public String getSerial() {
        return serial;
    }

    public String getNumber() {
        return number;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setIssuedPassport(String issuedPassport) {
        this.issuedPassport = issuedPassport;
    }

    public String getIssuedPassport() {
        return issuedPassport;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (number != null ? !number.equals(passport.number) : passport.number != null) return false;
        if (serial != null ? !serial.equals(passport.serial) : passport.serial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serial != null ? serial.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
