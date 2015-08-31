package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.dictionary.company.ContractPerson;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.JuridicalPerson.LegalForm;

import java.util.List;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CustomerModel.class)
public class CustomerDTO extends JuridicalPersonDTO {

    private static final long serialVersionUID = 9064031765692017661L;

    private List<CustomerPersonDTO> persons;

    @JsonManagedReference("persons")
    public List<CustomerPersonDTO> getPersons() {
        return persons;
    }

    public void setPersons(List<CustomerPersonDTO> persons) {
        this.persons = persons;
    }
}
