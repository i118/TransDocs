package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by konstantinchipunov on 21.08.14.
 * Реквезиты юридического лица
 */
@Embeddable
@Access(AccessType.FIELD)
@JsonTypeName(LegalAccountDetails.JSON_TYPE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegalAccountDetails implements IAccountDetails {

    public static final String JSON_TYPE_NAME = "account_details";

    @JsonProperty("inn")
    private String INN;

    @JsonProperty("kpp")
    private String KPP;

    @JsonProperty("bic")
    private String BIC;

    @JsonProperty("okpo")
    private String OKPO;

    @JsonProperty("ogrn")
    private String OGRN;

    @JsonProperty("okved")
    private String OKVED;

    @JsonProperty
    private String bank;

    @JsonProperty
    private String director;

    @JsonProperty
    private String chiefAccountant;

    @JsonProperty
    private String account;

    @JsonProperty
    private String correspondentAccount;

    public static class Columns {
        public static final String INN = "inn";

        public static final String KPP = "kpp";

        public static final String BIC = "bic";

        public static final String OKPO = "okpo";

        public static final String OGRN = "ogrn";

        public static final String OKVED= "okved";

        public static final String BANK = "bank";

        public static final String DIRECTOR = "director";

        public static final String CHIEF_ACCOUNT= "chief_account";

        public static final String ACCOUNT = "account";

        public static final String CORRESPONDENT_ACCOUNT= "correspondent_account";
    }

    @Override
    @Column(name = Columns.INN)
    public String getINN() {
        return INN;
    }

    @Override
    @Column(name = Columns.KPP)
    public String getKPP() {
        return KPP;
    }

    @Override
    @Column(name = Columns.BIC)
    public String getBIC() {
        return BIC;
    }

    @Override
    @Column(name = Columns.OKPO)
    public String getOKPO() {
        return OKPO;
    }

    @Override
    @Column(name = Columns.OGRN)
    public String getOGRN() {
        return OGRN;
    }

    @Override
    @Column(name = Columns.OKVED)
    public String getOKVED() {
        return OKVED;
    }

    @Override
    @Column(name = Columns.BANK)
    public String getBank() {
        return bank;
    }

    @Override
    @Column(name = Columns.DIRECTOR)
    public String getDirector() {
        return director;
    }

    @Override
    @Column(name = Columns.CHIEF_ACCOUNT)
    public String getChiefAccountant() {
        return chiefAccountant;
    }

    @Override
    @Column(name = Columns.ACCOUNT)
    public String getAccount() {
        return account;
    }

    @Override
    @Column(name = Columns.CORRESPONDENT_ACCOUNT)
    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    @Override
    //TODO Изменить сгенереный equals и hashCode
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LegalAccountDetails that = (LegalAccountDetails) o;

        if (BIC != null ? !BIC.equals(that.BIC) : that.BIC != null) return false;
        if (INN != null ? !INN.equals(that.INN) : that.INN != null) return false;
        if (KPP != null ? !KPP.equals(that.KPP) : that.KPP != null) return false;
        if (OGRN != null ? !OGRN.equals(that.OGRN) : that.OGRN != null) return false;
        if (OKPO != null ? !OKPO.equals(that.OKPO) : that.OKPO != null) return false;
        if (OKVED != null ? !OKVED.equals(that.OKVED) : that.OKVED != null) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (bank != null ? !bank.equals(that.bank) : that.bank != null) return false;
        if (chiefAccountant != null ? !chiefAccountant.equals(that.chiefAccountant) : that.chiefAccountant != null)
            return false;
        if (correspondentAccount != null ? !correspondentAccount.equals(that.correspondentAccount) : that.correspondentAccount != null)
            return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = INN != null ? INN.hashCode() : 0;
        result = 31 * result + (KPP != null ? KPP.hashCode() : 0);
        result = 31 * result + (BIC != null ? BIC.hashCode() : 0);
        result = 31 * result + (OKPO != null ? OKPO.hashCode() : 0);
        result = 31 * result + (OGRN != null ? OGRN.hashCode() : 0);
        result = 31 * result + (OKVED != null ? OKVED.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (chiefAccountant != null ? chiefAccountant.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (correspondentAccount != null ? correspondentAccount.hashCode() : 0);
        return result;
    }

    public static class AccountDetailsBuilder{
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

        private AccountDetailsBuilder() {}

        public static AccountDetailsBuilder with(){
            return new AccountDetailsBuilder();
        }

        public AccountDetailsBuilder setINN(String INN) {
            this.INN = INN;
            return this;
        }

        public AccountDetailsBuilder setKPP(String KPP) {
            this.KPP = KPP;
            return this;
        }

        public AccountDetailsBuilder setBIC(String BIC) {
            this.BIC = BIC;
            return this;
        }

        public AccountDetailsBuilder setOKPO(String OKPO) {
            this.OKPO = OKPO;
            return this;
        }

        public AccountDetailsBuilder setOGRN(String OGRN) {
            this.OGRN = OGRN;
            return this;
        }

        public AccountDetailsBuilder setOKVED(String OKVED) {
            this.OKVED = OKVED;
            return this;
        }

        public AccountDetailsBuilder setBank(String bank) {
            this.bank = bank;
            return this;
        }

        public AccountDetailsBuilder setDirector(String director) {
            this.director = director;
            return this;
        }

        public AccountDetailsBuilder setChiefAccountant(String chiefAccountant) {
            this.chiefAccountant = chiefAccountant;
            return this;
        }

        public AccountDetailsBuilder setAccount(String account) {
            this.account = account;
            return this;
        }

        public AccountDetailsBuilder setCorrespondentAccount(String correspondentAccount) {
            this.correspondentAccount = correspondentAccount;
            return this;
        }

        public LegalAccountDetails build(){
            LegalAccountDetails accountDetails = new LegalAccountDetails();
            accountDetails.INN = this.INN;
            accountDetails.BIC = this.BIC;
            accountDetails.KPP = this.KPP;
            accountDetails.OGRN =this.OGRN;
            accountDetails.OKPO = this.OKPO;
            accountDetails.OGRN = this.OGRN;
            accountDetails.director = this.director;
            accountDetails.bank = this.bank;
            accountDetails.chiefAccountant = this.chiefAccountant;
            accountDetails.account = this.account;
            accountDetails.correspondentAccount = this.correspondentAccount;
            accountDetails.OKVED = this.OKVED;
            return accountDetails;
        }
    }
}
