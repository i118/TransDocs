package com.td.model.entity.dictionary.user;

import com.td.model.entity.AbstractModel_;
import com.td.model.entity.dictionary.Person;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
@StaticMetamodel(UserModel.class)
public abstract class UserModel_ extends AbstractModel_ {

    public static volatile SingularAttribute<UserModel, String> phone;
    public static volatile SingularAttribute<UserModel, String> mail;
    public static volatile SingularAttribute<UserModel, String> login;
    public static volatile SingularAttribute<UserModel, Person.Gender> gender;
    public static volatile SingularAttribute<UserModel, String> patronymic;
    public static volatile SingularAttribute<UserModel, String> lastName;
    public static volatile SingularAttribute<UserModel, String> firstName;

}
