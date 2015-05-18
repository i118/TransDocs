package com.td.model.entity.dictionary.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.IPersistent;
import com.td.model.entity.dictionary.Dictionary;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.company.CompanyModel;

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
public interface IUserModel extends IPerson, Dictionary, IPersistent {
    public String getLogin();

    public void setLogin(String login);

    public IPassword getPassword();

    public void setPassword(IPassword password);

    public String getPhone();

    public void setPhone(String phone);

    public String getMail();

    public void setMail(String mail);

    public String getFirstName();

    public Set<IRoleModel> getRoleModels();

    public void setRoleModels(Set<IRoleModel> roleModels);

    public void addRoleModel(IRoleModel roleModel);

    @JsonBackReference("users")
    public CompanyModel getCompany();

    public void setCompany(CompanyModel company);
}
