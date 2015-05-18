package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.td.model.entity.IPersistent;
import com.td.model.entity.dictionary.Dictionary;

/**
 * Created by konstantinchipunov on 20.08.14.
 * Интерфейс контрагента
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME)
})
public interface JuridicalPerson extends Dictionary, IPersistent{

    /**
     * Полное наименование
     * @return полное наименование контрагента
     */
    public String getFullName();

    /**
     * Полное наименование
     * @param fullName полное наименование контрагента
     */
    public void setFullName(String fullName);

    /**
     * Краткое наименование
     * @return краткое наименование котр агента
     */
    public String getShortName();

    /**
     * Краткое наименование
     * @param shortName краткое наименование котрагента
     */
    public void setShortName(String shortName);

    /**
     * Организационно-правовая форма
     * @return Организационно-правовая форма контреагента
     */
    public LegalForm getLegalForm();

    /**
     * Организационно-правовая форма контреагента
     * @param form  Организационно-правовая форма контреагента
     */
    public void setLegalForm(LegalForm form);

    /**
     * Юр. Адрес
     * @return   Юр. Адрес контрагента
     */
    public String getLegalAddress();

    /**
     * Юр. Адрес контрагента
     * @param string Юр. Адрес контрагента
     */
    public void setLegalAddress(String string);

    /**
     * Почтовый адрес контрагента
     * @return Почтовый адрес контрагента
     */
    public String getMailingAddress();

    /**
     * Почтовый адрес контрагента
     * @param string Почтовый адрес контрагента
     */
    public void setMailingAddress(String string);

    /**
     * Телефон контрагента
     * @return  Телефон контрагента
     */
    public String getPhone();

    /**
     * Телефон контрагента
     * @param phone Телефон контрагента
     */
    public void setPhone(String phone);

    /**
     * Електронный адрес контрагента
     * @return   Електронный адрес контрагента
     */
    public String getEmail();

    /**
     *  Електронный адрес контрагента
     * @param email   Електронный адрес контрагента
     */
    public void setEmail(String email);


    /**
     * Комментарий к контрагенту
     * @return коментарий
     */
    public String getComment();

    /**
     * Комментарий к контрагенту
     * @param comment коментарий
     */
    public void setComment(String comment);

    /**
     * Реквезиты
     * @return Реквезиты
     */
    public IAccountDetails getAccountDetails();

    /**
     * Реквезиты
     * @param accountDetails Реквезиты
     */
    public void setAccountDetails(IAccountDetails accountDetails);

    /**
     * Виды организвционно правовых форм
     */
    public enum LegalForm {
        //ОАО(Open Joint-Stock Company)
        OJSC,
        //ООО(Limited Liability Company)
        LLC,
        //ЗАО(Closed Joint-Stock Company)
        CJSC,
        //ИП(individual businessman)
        IB;

        @JsonCreator
        public static  LegalForm forValue(String value){
            try {
                return LegalForm.valueOf(value);
            }catch (IllegalArgumentException e){
                return null;
            }
        }

        @JsonValue
        public String toValue(){
            return   this.toString();
        }
    }
}
