package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.validation.annotation.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 27.11.14.
 */
@Entity
@Table(name = CarModel.TABLE_NAME)
@JsonTypeName(CarModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarModel extends AbstractModel {
    private static final long serialVersionUID = -5746679152707163921L;

    public static final String TABLE_NAME = "td_car";

    @Size(max = 150, message = "{car.brand.MaxValue}")
    @NotEmpty(message = "{car.brand.notEmpty}")
    private String carBrand;

    @Size(max = 20, message = "{car.number.MaxValue}")
    @NotEmpty(message = "{car.number.notEmpty}")
    private String carNumber;

    private String trailerBrand;

    private String trailerNumber;

    private String trailerType;

    private String capacity;

    private String cubage;

    private CarrierModel carrier;

    private Set<DriverModel> drivers;

    public static class Columns extends AbstractModel.Columns {
        public static final String CAR_BRAND = "car_brand";
        public static final String CAR_NUMBER = "car_number";
        public static final String TRAILER_BRAND = "trailer_brand";
        public static final String TRAILER_NUMBER = "trailer_number";
        public static final String TRAILER_TYPE = "trailer_type";
        public static final String CAPACITY = "capacity";
        public static final String CUBAGE = "cubage";
        public static final String CARRIER_OBJECT_ID = "carrier_object_id";
    }

    @Column(name = Columns.CAR_BRAND, nullable = false, length = 255)
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    @Column(name = Columns.CAR_NUMBER, nullable = false, length = 20)
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Column(name = Columns.TRAILER_BRAND)
    public String getTrailerBrand() {
        return trailerBrand;
    }

    public void setTrailerBrand(String trailerBrand) {
        this.trailerBrand = trailerBrand;
    }

    @Column(name = Columns.TRAILER_NUMBER)
    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    @Column(name = Columns.TRAILER_TYPE)
    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    @Column(name = Columns.CAPACITY)
    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Column(name = Columns.CUBAGE)
    public String getCubage() {
        return cubage;
    }


    @Transient
    public UUID getCarrierId() {
        return getCarrier() != null ? getCarrier().getObjectId() : null;
    }

    public void setCubage(String cubage) {
        this.cubage = cubage;
    }

    @ManyToOne
    @JoinColumn(name = Columns.CARRIER_OBJECT_ID, nullable = true)
    @JsonBackReference("carrier-cars")
    public CarrierModel getCarrier() {
        return carrier;
    }

    public void setCarrier(CarrierModel carrier) {
        this.carrier = carrier;
    }

    @OneToMany(
            mappedBy = "car",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference("car-drivers")
    public Set<DriverModel> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<DriverModel> drivers) {
        if (drivers != null) {
            drivers.parallelStream().filter((DriverModel driver) -> driver.getCar() == null).forEach((DriverModel driver) -> driver.setCar(this));
        }
        this.drivers = drivers;
    }


    @PreUpdate
    private void preUpdate() {
        if (isDeleted() && getDrivers() != null) {
            getDrivers().forEach((DriverModel driver) -> driver.setCar(null));
            getDrivers().clear();
        }
    }

    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
