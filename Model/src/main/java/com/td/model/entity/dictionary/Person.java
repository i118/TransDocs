package com.td.model.entity.dictionary;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.util.StringUtils;

import static com.td.model.utils.EntityUtils.buildPersonDescription;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 06.11.13
 * Time: 23:10
 * To change this template use File | Settings | File Templates.
 *
 * Интерфейс представляющий человека
 */
public interface Person {

    enum Gender {
        MAN, WOMAN;

        @JsonCreator
        public static  Gender forValue(String value){
           try {
               return Gender.valueOf(value);
           }catch (IllegalArgumentException e){
               return null;
           }
        }

        @JsonValue
        public String toValue(){
          return   this.toString();
        }
    }

    /**
     * Имя
     * @return
     */
    public String getFirstName();

    public void setFirstName(String value);

    /**
     * Фамилия
     * @return
     */
    public String getLastName();

    public void setLastName(String value);

    /**
     * Отчество
     * @return
     */
    public String getPatronymic();

    public void setPatronymic(String value);

    /**
     * Пол
     * @return
     */
    public Gender getGender();

    public void setGender(Gender value);

    /**
     * Возвращает полное имя формата Фамилия И. О.
     * @return  Полное имя формата Фамилия И. О.
     */
    public default String getFullName() {
        return buildPersonDescription(getFirstName(), getLastName(), getPatronymic());
    }
}
