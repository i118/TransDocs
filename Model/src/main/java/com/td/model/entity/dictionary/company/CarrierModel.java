package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.file.*;
import com.td.model.listener.ContractorListener;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.*;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Entity
@Table(name = CarrierModel.TABLE_NAME)
@JsonTypeName(CarrierModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"files"})
@DiscriminatorValue(CarrierModel.TABLE_NAME)
@EntityListeners({ContractorListener.class})
public class CarrierModel extends JuridicalPersonModel implements FileContainer, Contractor {

    public static final String TABLE_NAME = "td_carrier";

    private List<ContractPerson> persons;

    private Attachment<CarrierModel> fileStore;

    private List<DriverModel> drivers;

    private List<CarModel> cars;

    public static class Columns extends JuridicalPersonModel.Columns{

    }

    public static class Relations extends JuridicalPersonModel.Relations{

    }

    @Override
    @OneToMany(
            mappedBy = "contractor",
            targetEntity = CarrierPersonModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("persons")
    public List<ContractPerson> getPersons() {
        return persons;
    }

    public void setPersons(List<ContractPerson> persons) {
        this.persons = persons;
    }

    @Override
    public void addPerson(ContractPerson person) {
        if(persons==null){
            persons = new ArrayList<>();
        }
        persons.add(person);
        person.setContractor(this);
    }


    @OneToOne(fetch = FetchType.EAGER,
            targetEntity = CarrierFileModel.class,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JsonManagedReference("fileStore")
    public Attachment<CarrierModel> getFileStore() {
        return fileStore;
    }

    public void setFileStore(Attachment<CarrierModel> fileStore) {
        this.fileStore = fileStore;
    }

    @Transient
    public UUID getFileStoreId(){
        return this.fileStore!=null ? fileStore.getObjectId() : null;
    }

    @Override
    @Transient
    public List<FileModel> getFiles() {
        if(fileStore==null){
            CarrierFileModel fileModel = new CarrierFileModel ();
            fileModel.setOwner(this);
            fileModel.setName(fileModel.getObjectId().toString());
            fileModel.setMimeType("");
            fileModel.setExtension("");
            fileModel.setFileType(IFileModel.FileType.STORE);
            setFileStore(fileModel);
        }
        return getFileStore().getFiles();
    }

    @Override
    public void setFiles(List<FileModel> files) {
        getFileStore().setFiles(files);
    }

    public void addFile(FileModel fileModel){
        getFileStore().addFile(fileModel);
    }

    @OneToMany(
            mappedBy = "carrier",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE}
    )
    @JsonManagedReference("carrier-drivers")
    public List<DriverModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverModel> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(DriverModel driver) {
        if(drivers==null){
            drivers = new ArrayList<>();
        }
        drivers.add(driver);
        driver.setCarrier(this);
    }

    @OneToMany(
            mappedBy = "carrier",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE}
    )
    @JsonManagedReference("carrier-cars")
    public List<CarModel> getCars() {
        return cars;
    }

    public void addCar(CarModel carModel){
        if(cars==null){
            cars = new ArrayList<>();
        }
        cars.add(carModel);
        carModel.setCarrier(this);
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

}
