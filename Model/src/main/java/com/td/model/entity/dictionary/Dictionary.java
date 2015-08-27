package com.td.model.entity.dictionary;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.company.*;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.UserModel;

/**
 * Created by zerotul.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserModel.class, name = UserModel.TABLE_NAME),
        @JsonSubTypes.Type(value = RoleModel.class, name = RoleModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME),
        @JsonSubTypes.Type(value = SimpleDictionary.class, name = SimpleDictionary.TABLE_NAME)
      })
public interface Dictionary extends Persistent {

    public String getDescription();

    public UserModel getOwner();

    public void setOwner(UserModel owner);
}
