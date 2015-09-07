package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Model;
import com.td.model.entity.Persistent;

import java.util.List;
import java.util.Set;

/**
 * Created by zerotul.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME)
})
public interface Contractor extends Persistent {
    /**
     * Контактные лица
     * @return  Контактные лица контрагента
     */
    @JsonManagedReference("persons")
    public List<ContractPerson> getPersons();

    /**
     * Добовляет контактное лицо контрагенту
     * @param person контактное лицо
     */
    public void addPerson(ContractPerson person);

}
