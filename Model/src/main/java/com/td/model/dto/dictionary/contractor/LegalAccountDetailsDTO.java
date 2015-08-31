package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.dictionary.company.LegalAccountDetails;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = LegalAccountDetails.class)
public class LegalAccountDetailsDTO extends DirtySupportDTO {

    private static final long serialVersionUID = 7739552692813976021L;

    private String INN;
    private String KPP;
    private String BIC;
    private String OKPO;
    private String OGRN;
    private String OKVED;
    private String bank;
    private String director;
    private String chiefAccountant;
    private String account;
    private String correspondentAccount;

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public String getKPP() {
        return KPP;
    }

    public void setKPP(String KPP) {
        this.KPP = KPP;
    }

    public String getBIC() {
        return BIC;
    }

    public void setBIC(String BIC) {
        this.BIC = BIC;
    }

    public String getOKPO() {
        return OKPO;
    }

    public void setOKPO(String OKPO) {
        this.OKPO = OKPO;
    }

    public String getOGRN() {
        return OGRN;
    }

    public void setOGRN(String OGRN) {
        this.OGRN = OGRN;
    }

    public String getOKVED() {
        return OKVED;
    }

    public void setOKVED(String OKVED) {
        this.OKVED = OKVED;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getChiefAccountant() {
        return chiefAccountant;
    }

    public void setChiefAccountant(String chiefAccountant) {
        this.chiefAccountant = chiefAccountant;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }
}
