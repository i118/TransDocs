package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Model;

import java.util.Set;

/**
 * Created by zerotul.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME)
})
public interface Contractor extends Model {
    /**
     * Контактные лица
     * @return  Контактные лица контрагента
     */
    @JsonManagedReference("persons")
    public Set<ContractPerson> getPersons();

    /**
     * Добовляет контактное лицо контрагенту
     * @param person контактное лицо
     */
    public void addPerson(ContractPerson person);

}
