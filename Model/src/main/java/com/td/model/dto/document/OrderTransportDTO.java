package com.td.model.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.annotation.ExcludeMapping;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.contractor.CarDTO;
import com.td.model.dto.dictionary.contractor.DriverDTO;
import com.td.model.dto.dictionary.contractor.PassportDTO;
import com.td.model.dto.mapper.orika.OrderTransportMapper;
import com.td.model.entity.Passport;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.entity.document.OrderTransport;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = OrderTransport.class, customMapperName = OrderTransportMapper.NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderTransportDTO extends ModelDTO{
    private static final long serialVersionUID = 4941448237507566350L;

    private DriverDTO driver;

    private CarDTO car;

    private String trailer;


    private PassportDTO driverPassport;

    private String driverPhone;

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    @ExcludeMapping
    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverDTO driver) {
        this.driver = driver;
    }

    @ExcludeMapping
    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public PassportDTO getDriverPassport() {
        return driverPassport;
    }

    public void setDriverPassport(PassportDTO driverPassport) {
        this.driverPassport = driverPassport;
    }

}
