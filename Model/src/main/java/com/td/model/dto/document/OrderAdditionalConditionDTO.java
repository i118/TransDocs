package com.td.model.dto.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.document.OrderAdditionalCondition;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = OrderAdditionalCondition.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAdditionalConditionDTO extends ModelDTO {

    private static final long serialVersionUID = -3867168312014156244L;
    private String additionalCondition;

    private String penalty;

    private String agreementContent;

    public String getAdditionalCondition() {
        return additionalCondition;
    }

    public void setAdditionalCondition(String additionalCondition) {
        this.additionalCondition = additionalCondition;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public String getAgreementContent() {
        return agreementContent;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }
}
