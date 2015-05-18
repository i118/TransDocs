package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by konstantinchipunov on 01.09.14.
 */
@Entity
@Table(name =CustomerPersonModel.TABLE_NAME)
@JsonTypeName(CustomerPersonModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerPersonModel extends ContractPersonModel<CustomerModel> {

    public static final String TABLE_NAME = "td_customer_person";

    private CustomerModel contractor;

    @Override
    @ManyToOne(
            targetEntity = CustomerModel.class
    )
    @JsonBackReference("persons")
    public CustomerModel getContractor() {
        return contractor;
    }

    public void setContractor(CustomerModel contractor) {
        this.contractor = contractor;
    }


    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
