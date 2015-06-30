package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.DriverModel;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zerotul.
 */
@Entity
@JsonTypeName(OrderTransport.TABLE_NAME)
@Table(name = OrderTransport.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTransport extends AbstractModel{
    private static final long serialVersionUID = 8491781632817251877L;

    public static final String TABLE_NAME = "order_transport";

    private DriverModel driver;

    private CarModel car;

    private String trailer;

    private Passport driverPassport;

    private String driverPhone;

    public static class Columns extends AbstractModel.Columns{

        public static final String DRIVER_ID = "driver_id";
        public static final String CAR_ID = "car_id";
        public static final String TRAILER = "trailer";
        public static final String DRIVER_PASSPORT_NUMBER = "driver_passport_number";
        public static final String DRIVER_PASSPORT_SERIAL = "driver_passport_serial";
        public static final String DRIVER_ISSUED_PASSPORT = "driver_issued_passport";
        public static final String DRIVER_PHONE = "driver_phone";
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.DRIVER_ID)
    public DriverModel getDriver() {
        return driver;
    }

    public void setDriver(DriverModel driver) {
        this.driver = driver;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Columns.CAR_ID)
    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    @Column(name = Columns.TRAILER)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serial", column = @Column(name = Columns.DRIVER_PASSPORT_SERIAL)),
            @AttributeOverride(name = "number", column = @Column(name = Columns.DRIVER_PASSPORT_NUMBER)),
            @AttributeOverride(name = "issuedPassport", column = @Column(name = Columns.DRIVER_ISSUED_PASSPORT))
    })
    public Passport getDriverPassport() {
        return driverPassport;
    }

    public void setDriverPassport(Passport driverPassport) {
        this.driverPassport = driverPassport;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @Column(name = Columns.DRIVER_PHONE)
    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }
}
