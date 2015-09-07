package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.file.CarrierFileDTO;
import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.ContractPerson;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.entity.file.Attachment;

import java.util.List;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CarrierModel.class)
public class CarrierDTO extends JuridicalPersonDTO {

    private static final long serialVersionUID = -5518306496361596512L;

    private List<CarrierPersonDTO> persons;

    private CarrierFileDTO fileStore;

    private List<DriverDTO> drivers;

    private List<CarDTO> cars;

    @JsonManagedReference("carrier-drivers")
    public List<DriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverDTO> drivers) {
        this.drivers = drivers;
    }

    @JsonManagedReference("carrier-cars")
    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    @JsonManagedReference("fileStore")
    public CarrierFileDTO getFileStore() {
        return fileStore;
    }

    public void setFileStore(CarrierFileDTO fileStore) {
        this.fileStore = fileStore;
    }

    @JsonManagedReference("persons")
    public List<CarrierPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<CarrierPersonDTO> persons) {
        this.persons = persons;
    }
}
