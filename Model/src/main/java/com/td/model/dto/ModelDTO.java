package com.td.model.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.td.model.annotation.SkipDirty;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ModelDTO extends DirtySupportDTO {

    private UUID objectId;

    private Date creationDate;

    private Date modifyDate;

    private Long version = -1l;

    private String objectType;

    private Boolean deleted = false;

    public UUID getObjectId() {
        return objectId;
    }

    @SkipDirty
    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @SkipDirty
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    @SkipDirty
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getObjectType() {
        return objectType;
    }

    @SkipDirty
    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
