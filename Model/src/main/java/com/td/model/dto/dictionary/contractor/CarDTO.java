package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.validation.annotation.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CarModel.class)
public class CarDTO extends ModelDTO {

    private static final long serialVersionUID = -5070809926868023871L;
    private String carBrand;

    private String carNumber;

    private String trailerBrand;

    private String trailerNumber;

    private String trailerType;

    private String capacity;

    private String cubage;

    private CarrierDTO carrier;

    private Set<DriverDTO> drivers;

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTrailerBrand() {
        return trailerBrand;
    }

    public void setTrailerBrand(String trailerBrand) {
        this.trailerBrand = trailerBrand;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCubage() {
        return cubage;
    }

    public void setCubage(String cubage) {
        this.cubage = cubage;
    }

    @JsonBackReference("carrier-cars")
    public CarrierDTO getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierDTO carrier) {
        this.carrier = carrier;
    }
//
//    @JsonManagedReference("car-drivers")
//    public Set<DriverDTO> getDrivers() {
//        return drivers;
//    }
//
//    public void setDrivers(Set<DriverDTO> drivers) {
//        this.drivers = drivers;
//    }
}
