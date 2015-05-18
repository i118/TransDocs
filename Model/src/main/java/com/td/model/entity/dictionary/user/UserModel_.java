package com.td.model.entity.dictionary.user;

import com.td.model.entity.AbstractModel_;
import com.td.model.entity.dictionary.IPerson;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
@StaticMetamodel(UserModel.class)
public abstract class UserModel_ extends AbstractModel_ {

    public static volatile SingularAttribute<IUserModel, String> phone;
    public static volatile SingularAttribute<IUserModel, String> mail;
    public static volatile SingularAttribute<IUserModel, String> login;
    public static volatile SingularAttribute<IUserModel, IPerson.Gender> gender;
    public static volatile SingularAttribute<IUserModel, String> patronymic;
    public static volatile SingularAttribute<IUserModel, String> lastName;
    public static volatile SingularAttribute<IUserModel, String> firstName;

}
