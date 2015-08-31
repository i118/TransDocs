package com.td.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by zerotul.
 */
public abstract class DirtySupportDTO implements Serializable {

    private Set<String> dirtyFields;

    public DirtySupportDTO() {
        dirtyFields = new HashSet<>();
    }

    public boolean isDirtyField(String fieldName){
        return dirtyFields.contains(fieldName);
    }

    public void addDirtyField(String field){
        dirtyFields.add(field);
    }

    public Set<String> getDirtyFields() {
        return dirtyFields;
    }

}
