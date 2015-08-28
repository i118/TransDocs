package com.td.model.entity.dictionary.role;

import com.td.model.entity.AbstractModel_;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
@StaticMetamodel(RoleModel.class)
public abstract class RoleModel_ extends AbstractModel_ {

    public static volatile SingularAttribute<RoleModel, String> roleName;

    public static volatile SingularAttribute<RoleModel, String> description;


}
