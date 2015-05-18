package com.td.model.entity.lock;

import com.td.model.entity.IModel;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface ILockable extends IModel{

    public ILock getLockObject();

    public void setLockObject(ILock lock);
}
