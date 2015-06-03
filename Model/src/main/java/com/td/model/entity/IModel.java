package com.td.model.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 03.01.14.
 */
public interface IModel extends Serializable {
    public UUID getObjectId();
}