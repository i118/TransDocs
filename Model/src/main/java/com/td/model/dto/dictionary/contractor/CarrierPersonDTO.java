package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CarrierPersonModel;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CarrierPersonModel.class)
public class CarrierPersonDTO extends ContractPersonDTO {
    private static final long serialVersionUID = -6364276753901560613L;

    private CarrierDTO contractor;

    @JsonBackReference("persons")
    public CarrierDTO getContractor() {
        return contractor;
    }

    public void setContractor(CarrierDTO contractor) {
        this.contractor = contractor;
    }
}
