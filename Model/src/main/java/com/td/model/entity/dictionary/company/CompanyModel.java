package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.listener.ModelModificationListener;
import com.td.model.multitenant.SchemaProviderImpl;
import com.td.model.validation.annotation.CompanyValid;
import com.td.model.validation.annotation.UniqueCompany;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by zerotul on 27.01.15.
 */
@Entity
@CompanyValid
@UniqueCompany
@Table(name = CompanyModel.TABLE_NAME)
@JsonTypeName(CompanyModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
@ExcludeSuperclassListeners
@EntityListeners({ModelModificationListener.class})
public class CompanyModel extends JuridicalPersonModel {

    public static final String TABLE_NAME = "td_company";

    private static final long serialVersionUID = 294505326356415655L;

    private Set<UserModel> users;

    private String login;

    public static class Columns extends JuridicalPersonModel.Columns{
        public static final String LOGIN = "login";
    }

    @Column(name = Columns.LOGIN, nullable = false, updatable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @OneToMany(
            targetEntity = UserModel.class,
            mappedBy = "company"
    )
    @JsonManagedReference("users")
    public Set<UserModel> getUsers() {
        return users;
    }

    public void setUsers(Set<UserModel> users) {
        this.users = users;
    }
}
