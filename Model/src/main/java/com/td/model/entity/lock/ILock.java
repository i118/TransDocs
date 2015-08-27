package com.td.model.entity.lock;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Persistent;

import java.util.UUID;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
@JsonSubTypes({ @JsonSubTypes.Type(value = Lock.class, name = Lock.TABLE_NAME)})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
public interface ILock extends Persistent {

    public String getLockOwner();

    public void setLockOwner(String lockOwner);

    public UUID getLockedObjectId();

    public void setLockedObjectId(UUID persistent);
}
