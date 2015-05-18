package com.td.service.dto;

import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.company.DriverModel;

import java.util.UUID;

/**
 * Created by konstantinchipunov on 03.12.14.
 */
public class DriverDTO extends AbstractModelDTO {

    private static final long serialVersionUID = 5607242186681811821L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private IPerson.Gender gender;

    private Passport passport;

    private String phone;

    private String registrationAddress;

    private String drivingLicense;

    private UUID defaultCarId;

    private UUID carrierId;

    public DriverDTO(DriverModel driver) {
        super(driver);
        build(driver);
    }

    private void build(DriverModel driver) {
        this.firstName = driver.getFirstName();
        this.lastName = driver.getLastName();
        this.patronymic = driver.getPatronymic();
        this.passport = driver.getPassport();
        this.gender = driver.getGender();
        this.phone = driver.getPhone();
        this.registrationAddress = driver.getRegistrationAddress();
        this.drivingLicense = driver.getDrivingLicense();
        if (driver.getCar() != null) {
            this.defaultCarId = driver.getCar().getObjectId();
        }

        carrierId = driver.getCarrier().getObjectId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public IPerson.Gender getGender() {
        return gender;
    }

    public Passport getPassport() {
        return passport;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public UUID getDefaultCarId() {
        return defaultCarId;
    }

    public UUID getCarrierId() {
        return carrierId;
    }
}
