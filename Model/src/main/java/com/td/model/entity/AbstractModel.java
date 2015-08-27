package com.td.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.td.model.listener.ModelModificationListener;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 06.11.13
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 *
 * Абстракный класс модели
 */

@MappedSuperclass
@EntityListeners({ModelModificationListener.class})
public abstract class AbstractModel implements Persistent {

    {
        generateId();
        setObjectType(getTableName());
    }

    private UUID objectId;

    private Date creationDate;

    private Date modifyDate;

    private Long version = -1l;

    private String objectType;

    private Boolean deleted = false;

    public AbstractModel(){}

    public static class Columns{

        public static final String OBJECT_ID = "object_id";

        public static final String VERSION = "version";

        public static final String CREATION_DATE = "creation_date";

        public static final String MODIFY_DATE = "modify_date";

        public static final String OBJECT_TYPE = "object_type";

        public static final String IS_DELETED = "is_deleted";
    }

    public static class Relations{}


    @Id
    @Override
    @Type(type="org.hibernate.type.PostgresUUIDType")
    @Column(name = Columns.OBJECT_ID)
    public UUID getObjectId() {
        return objectId;
    }

    @Override
    public void setObjectId(UUID objectId) {
      if( objectId!=null){
        this.objectId = objectId;
      }
    }

    @Column(name = Columns.CREATION_DATE, nullable = false, columnDefinition = "DATETIME")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = Columns.MODIFY_DATE, nullable = true, columnDefinition = "DATETIME")
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Transient
    public boolean isNew() {
        return version==-1;
    }

    @Version
    @Override
    @Column(name=Columns.VERSION)
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    @Column(name = Columns.IS_DELETED)
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    @JsonIgnore
    @Column(name=Columns.OBJECT_TYPE, nullable = false)
    public String getObjectType() {
        return objectType;
    }

    @JsonIgnore
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    @Transient
    public abstract String getTableName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractModel that = (AbstractModel) o;

        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return objectId.hashCode();
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "objectId=" + objectId +
                '}';
    }

    protected void generateId(){
        setObjectId(UUID.randomUUID());
    }

}
