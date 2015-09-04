package com.td.model.dto.dictionary.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.dictionary.DictionaryDTO;
import com.td.model.dto.dictionary.contractor.CompanyDTO;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.IPassword;
import com.td.model.entity.dictionary.user.UserModel;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = UserModel.class)
public class UserDTO extends DictionaryDTO {

    private static final long serialVersionUID = -7661593875388054754L;

    private String firstName;

    private String lastName;

    private String patronymic;

    private Person.Gender gender;

    private String login;

    private String mail;

    private String phone;

    private String description;

    private Set<RoleDTO> roleModels;

    private CompanyDTO company;

    @JsonBackReference("users")
    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Person.Gender getGender() {
        return gender;
    }

    public void setGender(Person.Gender gender) {
        this.gender = gender;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleDTO> getRoleModels() {
        return roleModels;
    }

    public void setRoleModels(Set<RoleDTO> roleModels) {
        this.roleModels = roleModels;
    }
}
