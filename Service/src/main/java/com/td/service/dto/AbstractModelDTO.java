package com.td.service.dto;

import com.td.model.entity.Persistent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class AbstractModelDTO implements Persistent {

    private UUID objectId;

    private Date creationDate;

    private Date modifyDate;

    private Long version = -1l;

    private String objectType;

    private Boolean deleted = false;

    private String tableName;

    public AbstractModelDTO(){}

    public AbstractModelDTO(Persistent persistent){
      this.build(persistent);
    }

    private void build(Persistent persistent){
       this.objectId = persistent.getObjectId();
        this.creationDate = persistent.getCreationDate();
        this.modifyDate = persistent.getModifyDate();
        this.version = persistent.getVersion();
        this.objectType = persistent.getObjectType();
        this.deleted = persistent.isDeleted();
    }

    public UUID getObjectId() {
        return objectId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public long getVersion() {
        return version;
    }

    public String getObjectType() {
        return objectType;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean isNew() {
        return getVersion()==-1;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(boolean value) {
      deleted = value;
    }

    @Override
    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
       this.tableName = tableName;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

}
