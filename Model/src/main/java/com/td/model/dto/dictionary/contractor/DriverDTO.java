package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.annotation.ExcludeMapping;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.mapper.orika.DriverCustomMapper;
import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.validation.annotation.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = DriverModel.class, customMapperName = DriverCustomMapper.NAME)
public class DriverDTO extends ModelDTO {

    private static final long serialVersionUID = -5756848217528177225L;
    private String firstName;

    private String lastName;

    private String patronymic;

    private Person.Gender gender;

    private PassportDTO passport;

    private String phone;

    private String registrationAddress;

    private String drivingLicense;

    private CarDTO car;

    private CarrierDTO carrier;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Person.Gender getGender() {
        return gender;
    }

    public void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    public PassportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportDTO passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    @ExcludeMapping
    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    @JsonBackReference("carrier-drivers")
    public CarrierDTO getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierDTO carrier) {
        this.carrier = carrier;
    }
}
