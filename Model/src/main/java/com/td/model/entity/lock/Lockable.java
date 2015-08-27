package com.td.model.entity.lock;

import com.td.model.entity.Model;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface Lockable extends Model {

    public ILock getLockObject();

    public void setLockObject(ILock lock);
}
