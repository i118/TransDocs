package com.td.model.entity.dictionary;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.IModel;
import com.td.model.entity.IPersistent;
import com.td.model.entity.dictionary.company.*;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.UserModel;

import java.util.Set;

/**
 * Created by konstantinchipunov on 14.08.14.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserModel.class, name = UserModel.TABLE_NAME),
        @JsonSubTypes.Type(value = RoleModel.class, name = RoleModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME),
        @JsonSubTypes.Type(value = SimpleDictionary.class, name = SimpleDictionary.TABLE_NAME)
      })
public interface Dictionary extends IPersistent {

    public String getDescription();

    public UserModel getOwner();

    public void setOwner(UserModel owner);
}
