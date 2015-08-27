package com.td.model.entity;

import com.td.model.entity.dictionary.Dictionary;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Date;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
public class AbstractModel_<T extends Persistent & Dictionary> {

    public static volatile SingularAttribute<Persistent, UUID> objectId;
    public static volatile SingularAttribute<Persistent, Date> creationDate;
    public static volatile SingularAttribute<Persistent, Date> modifyDate;
    public static volatile SingularAttribute<Persistent, Long> version;
    public static volatile SingularAttribute<Persistent, String> objectType;
    public static volatile SingularAttribute<Persistent, Boolean> deleted;

}
