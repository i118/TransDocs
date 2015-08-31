package com.td.model.dto.dictionary.contractor;

import com.td.model.dto.dictionary.DictionaryDTO;
import com.td.model.entity.dictionary.company.JuridicalPerson;

/**
 * Created by zerotul.
 */
public abstract class JuridicalPersonDTO extends DictionaryDTO {
    private String fullName;

    private String shortName;

    private JuridicalPerson.LegalForm legalForm;

    private String legalAddress;

    private String mailingAddress;

    private String phone;

    private String email;

    private String comment;

    private LegalAccountDetailsDTO accountDetails;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public JuridicalPerson.LegalForm getLegalForm() {
        return legalForm;
    }

    public void setLegalForm(JuridicalPerson.LegalForm legalForm) {
        this.legalForm = legalForm;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LegalAccountDetailsDTO getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(LegalAccountDetailsDTO accountDetails) {
        this.accountDetails = accountDetails;
    }
}
