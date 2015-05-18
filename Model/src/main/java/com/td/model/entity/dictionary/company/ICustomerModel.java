package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.file.IFileContainer;

/**
 * Created by konstantinchipunov on 13.08.14.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME)})
public interface ICustomerModel extends JuridicalPerson, IFileContainer, Contractor {
}
