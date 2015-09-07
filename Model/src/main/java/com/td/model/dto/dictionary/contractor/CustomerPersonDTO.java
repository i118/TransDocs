package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.td.model.annotation.DTO;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.CustomerPersonModel;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CustomerPersonModel.class)
public class CustomerPersonDTO extends ContractPersonDTO {

    private CustomerDTO contractor;

    @JsonBackReference("persons")
    public CustomerDTO getContractor() {
        return contractor;
    }

    public void setContractor(CustomerDTO contractor) {
        this.contractor = contractor;
    }
}
