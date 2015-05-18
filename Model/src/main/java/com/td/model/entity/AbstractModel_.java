package com.td.model.entity;

import com.td.model.entity.dictionary.Dictionary;

import javax.persistence.metamodel.SingularAttribute;
import java.util.Date;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 25.07.14.
 */
public class AbstractModel_<T extends IPersistent & Dictionary> {

    public static volatile SingularAttribute<IPersistent, UUID> objectId;
    public static volatile SingularAttribute<IPersistent, Date> creationDate;
    public static volatile SingularAttribute<IPersistent, Date> modifyDate;
    public static volatile SingularAttribute<IPersistent, Long> version;
    public static volatile SingularAttribute<IPersistent, String> objectType;
    public static volatile SingularAttribute<IPersistent, Boolean> deleted;

}
