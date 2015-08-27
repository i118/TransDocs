package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.Person;

/**
 * Created by konstantinchipunov on 20.08.14.
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerPersonModel.class, name = CustomerPersonModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierPersonModel.class, name = CarrierPersonModel.TABLE_NAME)
})
public interface ContractPerson<T extends Contractor> extends Person, Persistent {

    /**
     * Телефон контактного лица
     * @return  Телефон контактного лица
     */
    public String getPhone();

    /**
     * Телефон контактного лица
     * @param phone Телефон контактного лица
     */
    public void setPhone(String phone);

    /**
     * Електронный адрес контактного лица
     * @return   Електронный адрес контактного лица
     */
    public String getEmail();

    /**
     *  Електронный адрес контактного лица
     * @param email   Електронный адрес контактного лица
     */
    public void setEmail(String email);

    /**
     * Контрагент
     */
    @JsonBackReference("persons")
    public T getContractor();

    /**
     * Контрагент
     */
    public void setContractor(T contractorModel);

}
