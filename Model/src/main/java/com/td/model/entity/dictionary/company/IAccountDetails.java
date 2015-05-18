package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embeddable;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.io.Serializable;

/**
 * Created by konstantinchipunov on 20.08.14.
 * Реквезиты
 */
@Embeddable
@Access(AccessType.FIELD)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = LegalAccountDetails.class, name = LegalAccountDetails.JSON_TYPE_NAME)})
@DiscriminatorColumn(name=IAccountDetails.Columns.EMBEDDED_TYPE, discriminatorType= DiscriminatorType.STRING)
public interface IAccountDetails extends Serializable, Cloneable{


    public static class Columns{
        public static final String EMBEDDED_TYPE = "embedded_type";
    }

    /**
     * ИНН
     * @return ИНН
     */
    public String getINN();

    /**
     * КПП
     * @return КПП
     */
    public String getKPP();

    /**
     * БИК
     * @return БИК
     */
    public String getBIC();

    /**
     * ОКПО
     * @return ОКПО
     */
    public String getOKPO();


    /**
     * ОГРН
     * @return ОГРН
     */
    public String getOGRN();

    /**
     * ОКВЭД
     * @return ОКВЭД
     */
    public String getOKVED();

    /**
     * Банк
     * @return Банк
     */
    public String getBank();

    /**
     * Директор
     * @return директор
     */
    public String getDirector();


    /**
     * Главный бухгалтер
     * @return Главный бухгалтер
     */
    public String getChiefAccountant();


    /**
     * Расчетный счет
     * @return Расчетный счет
     */
    public String getAccount();

    /**
     * Корреспондентский счет
     * @return  Корреспондентский счет
     */
    public String getCorrespondentAccount();

}
