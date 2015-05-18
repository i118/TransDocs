package com.td.model.entity.lock;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
@Entity
@JsonAutoDetect
@JsonTypeName(Lock.TABLE_NAME)
@Table(name = Lock.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lock extends AbstractModel implements ILock {

    public static final String TABLE_NAME = "td_lock";

    private String lockOwner;

    private UUID lockObjectId;


    public static class Columns extends AbstractModel.Columns{
        public static final String LOCK_OWNER = "lock_owner";

        public static final String LOCK_OBJECT_ID = "lock_object_id";
    }

    @Override
    @Column(name = Columns.LOCK_OWNER, nullable = false, updatable = false)
    public String getLockOwner() {
        return lockOwner;
    }

    @Override
    public void setLockOwner(String lockOwner) {
       this.lockOwner = lockOwner;
    }

    @Override
    @Type(type="uuid-char")
    @Column(name = Columns.LOCK_OBJECT_ID, nullable = false, updatable = false)
    public UUID getLockedObjectId() {
        return lockObjectId;
    }

    @Override
    public void setLockedObjectId(UUID id) {
         lockObjectId = id;
    }


    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }
}
