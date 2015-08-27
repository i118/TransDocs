package com.td.model.entity.dictionary.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.role.RoleModel;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 23.11.13
 * Time: 14:08
 * To change this template use File | Settings | File Templates.
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = UserModel.class, name = UserModel.TABLE_NAME)})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType" , visible = true)
public interface IUserModel extends Person, Dictionary, Persistent {
    public String getLogin();

    public void setLogin(String login);

    public IPassword getPassword();

    public void setPassword(IPassword password);

    public String getPhone();

    public void setPhone(String phone);

    public String getMail();

    public void setMail(String mail);

    public String getFirstName();

    public Set<RoleModel> getRoleModels();

    public void setRoleModels(Set<RoleModel> roleModels);

    public void addRoleModel(RoleModel roleModel);

    @JsonBackReference("users")
    public CompanyModel getCompany();

    public void setCompany(CompanyModel company);
}
