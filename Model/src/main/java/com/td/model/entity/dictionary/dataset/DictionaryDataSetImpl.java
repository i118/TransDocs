package com.td.model.entity.dictionary.dataset;

import java.util.UUID;

/**
 * Created by zerotul on 19.03.15.
 */
public class DictionaryDataSetImpl implements DictionaryDataSet{

    private static final long serialVersionUID = 3032053656156039935L;

    private String description;

    private UUID objectId;

    private boolean deleted;

    private long version;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public UUID getObjectId() {
        return objectId;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
