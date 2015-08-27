package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.AbstractDictionary;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ContractPersonModel<T extends Contractor> extends AbstractModel implements ContractPerson<T> {

    private static final long serialVersionUID = 1518957946766246802L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Gender gender;

    private String phone;

    private String email;

    public static class Columns extends AbstractDictionary.Columns{
        public static final String FIRST_NAME = "first_name";

        public static final String LAST_NAME = "last_name";

        public static final String PATRONYMIC = "patronymic";

        public static final String GENDER = "gender";

        public static final String PHONE = "phone";

        public static final String E_MAIL = "e_mail";
    }

    @Column(name = Columns.FIRST_NAME, unique = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = Columns.LAST_NAME, unique = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = Columns.PATRONYMIC, unique = false)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = Columns.GENDER, unique = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Column(name = Columns.PHONE, unique=false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = Columns.E_MAIL, unique=false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getDescription() {
        return getFullName();
    }

    @Transient
    public UUID getContractorId() {
        return getContractor()!=null ? getContractor().getObjectId() : null;
    }
}
