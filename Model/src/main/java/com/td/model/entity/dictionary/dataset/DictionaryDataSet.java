package com.td.model.entity.dictionary.dataset;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by zerotul on 19.03.15.
 */
public interface DictionaryDataSet extends Serializable{

    public String getDescription();

    public UUID getObjectId();

    public boolean isDeleted();

    public long getVersion();
}
