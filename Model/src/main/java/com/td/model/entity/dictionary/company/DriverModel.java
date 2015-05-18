package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.validation.annotation.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 27.11.14.
 */
@Entity
@JsonTypeName(DriverModel.TABLE_NAME)
@Table(name = DriverModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverModel extends AbstractModel implements IPerson {

    private static final long serialVersionUID = -1682907088202994166L;

    public static final String TABLE_NAME = "td_driver";

    private String firstName;

    @Size(max = 255, message = "{driver.lastName.MaxValue}")
    @NotEmpty(message = "{driver.lastName.notEmpty}")
    private String lastName;

    private String patronymic;

    private Gender gender;

    private Passport passport;

    private String phone;

    private String registrationAddress;

    private String drivingLicense;

    private CarModel car;

    private CarrierModel carrier;

    public static class Columns extends AbstractModel.Columns{
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PATRONYMIC = "patronymic";
        public static final String GENDER = "gender";
        public static final String PHONE = "phone";
        public static final String REGISTRATION_ADDRESS = "registration_address";
        public static final String DRIVING_LICENSE = "driving_license";
        public static final String CAR_ID = "car_id";
        public static final String CARRIER_ID = "carrier_object_id";
    }


    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    @Column(name = Columns.FIRST_NAME, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    @Column(name = Columns.LAST_NAME, nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    @Column(name = Columns.PATRONYMIC)
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    @Column(name = Columns.GENDER)
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Embedded
    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    @Column(name = Columns.PHONE)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = Columns.REGISTRATION_ADDRESS )
    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    @Column(name = Columns.DRIVING_LICENSE)
    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= Columns.CAR_ID)
    @JsonBackReference
    public CarModel getCar() {
        return car;
    }

    public void setCar(CarModel car) {
        this.car = car;
    }

    @ManyToOne
    @JsonBackReference("carrier-drivers")
    public CarrierModel getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierModel carrier) {
        this.carrier = carrier;
    }

    @Transient
    public UUID getCarrierId(){
        return getCarrier()!=null ? getCarrier().getObjectId() : null;
    }

    @Transient
    public UUID getDefaultCarId(){
        return getCar()!=null ? getCar().getObjectId() : null;
    }
}

