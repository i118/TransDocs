package com.td.model.listener;

import com.td.model.entity.Persistent;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by konstantinchipunov on 25.07.14.
 */

public class ModelModificationListener {

    @PrePersist
    public void persist(Persistent persistent){
        Date creationDate = new Date();
        persistent.setCreationDate(creationDate);
        persistent.setModifyDate(creationDate);
    }

    @PreUpdate
    public void update(Persistent persistent){
        persistent.setCreationDate(new Date());
    }
}
