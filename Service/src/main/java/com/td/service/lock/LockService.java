package com.td.service.lock;

import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.lock.Lockable;
import com.td.service.crud.CRUDService;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface LockService extends CRUDService {

    public void lock(Lockable lockable) throws LockException;

    public void unlock(Lockable lockable) throws LockException;

    public boolean isLockedByUser(Lockable lockable, IUserModel userModel);

    public boolean isLocked(Lockable lockable);
}
