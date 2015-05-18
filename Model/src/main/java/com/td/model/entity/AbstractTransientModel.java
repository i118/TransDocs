package com.td.model.entity;

import java.util.UUID;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
public abstract class AbstractTransientModel implements IModel {

    private UUID objectId;

    public void init(){
        this.objectId = UUID.randomUUID();
    }

    public UUID getObjectId() {
        return objectId;
    }
}
