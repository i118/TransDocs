package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.AbstractDictionary;
import com.td.model.validation.annotation.NotEmpty;
import org.hibernate.annotations.Target;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Transient;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JuridicalPersonModel extends AbstractDictionary implements JuridicalPerson {

    private static final long serialVersionUID = -5319410185753624151L;

    @NotEmpty(message = "{company.fullName.notEmpty}")
    private String fullName;

    private String shortName;

    private LegalForm legalForm;

    private String legalAddress;

    private String mailingAddress;

    private String phone;

    private String email;

    private String comment;

    private LegalAccountDetails accountDetails;

    public static class Columns extends AbstractDictionary.Columns{
        public static final String FULL_NAME = "full_name";

        public static final String SHORT_NAME= "short_name";

        public static final String LEGAL_FORM = "legal_form";

        public static final String MAILING_ADDRESS= "mailing_address";

        public static final String LEGAL_ADDRESS = "legal_address";

        public static final String PHONE = "PHONE";

        public static final String EMAIL= "email";

        public static final String COMMENT= "comment";
    }

    public static class Relations extends AbstractModel.Relations{

        public static final String CONTRACTOR_TO_PERSON = "contractor_to_person";

        public static final String CONTRACTOR_ID = "contractor_id";

        public static final String PERSON_ID = "person_id";
    }

    @Override
    @Column(name = Columns.FULL_NAME, nullable = false)
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = Columns.SHORT_NAME, nullable = false)
    public String getShortName() {
        return shortName;
    }

    @Override
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    @Column(name = Columns.LEGAL_FORM)
    @Enumerated(EnumType.STRING)
    public LegalForm getLegalForm() {
        return legalForm;
    }

    @Override
    public void setLegalForm(LegalForm legalForm) {
        this.legalForm = legalForm;
    }

    @Override
    @Column(name = Columns.LEGAL_ADDRESS)
    public String getLegalAddress() {
        return legalAddress;
    }

    @Override
    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    @Override
    @Column(name = Columns.MAILING_ADDRESS)
    public String getMailingAddress() {
        return mailingAddress;
    }

    @Override
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Override
    @Column(name = Columns.PHONE)
    public String getPhone() {
        return phone;
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    @Column(name = Columns.EMAIL)
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @Column(name = Columns.COMMENT)
    public String getComment() {
        return comment;
    }

    @Override
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    @Embedded
    @Target(LegalAccountDetails.class)
    public LegalAccountDetails getAccountDetails() {
        return accountDetails;
    }

    @Override
    public void setAccountDetails(LegalAccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }


    @Override
    @Transient
    public String getDescription() {
        return getFullName();
    }
}
