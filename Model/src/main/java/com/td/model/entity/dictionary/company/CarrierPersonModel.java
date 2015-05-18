package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Entity
@Table(name = CarrierPersonModel.TABLE_NAME)
@JsonTypeName(CarrierPersonModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarrierPersonModel extends ContractPersonModel<CarrierModel> {

    public static final String TABLE_NAME = "td_carrier_person";

    private CarrierModel contractor;

    public static class Columns extends ContractPersonModel.Columns{
        public static final String CONTRACTOR_OBJECT_ID = "contractor_object_id";
    }

    @Override
    @ManyToOne
    @JsonBackReference("persons")
    public CarrierModel getContractor() {
        return contractor;
    }

    public void setContractor(CarrierModel contractor) {
        this.contractor = contractor;
    }


    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

}
