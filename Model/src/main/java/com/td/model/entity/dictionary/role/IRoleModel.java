package com.td.model.entity.dictionary.role;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.dictionary.Dictionary;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = RoleModel.class, name = RoleModel.TABLE_NAME)})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
public interface IRoleModel extends Dictionary{

    public String getRoleName();

    public void setRoleName(String roleName);

    public void setDescription(String description);

    public String getDescription();
}
