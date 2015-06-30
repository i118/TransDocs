package com.td.model.entity.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by zerotul.
 */
@Entity
@JsonTypeName(OrderAdditionalCondition.TABLE_NAME)
@Table(name = OrderAdditionalCondition.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderAdditionalCondition extends AbstractModel{
    private static final long serialVersionUID = -7257176225153251608L;

    public static final String TABLE_NAME = "order_additional_condition";

    private String additionalCondition;

    private String penalty;

    private String agreementContent;

    public static class Columns extends AbstractModel.Columns{
        public static final String ADDITIONAL_CONDITION = "additional_condition";
        public static final String PENALTY = "penalty";
        public static final String AGREEMENT_CONTENT = "agreement_content";
    }

    @Column(name = Columns.ADDITIONAL_CONDITION)
    public String getAdditionalCondition() {
        return additionalCondition;
    }

    public void setAdditionalCondition(String additionalCondition) {
        this.additionalCondition = additionalCondition;
    }

    @Column(name = Columns.PENALTY)
    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    @Column(name = Columns.AGREEMENT_CONTENT, columnDefinition = "TEXT")
    public String getAgreementContent() {
        return agreementContent;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
