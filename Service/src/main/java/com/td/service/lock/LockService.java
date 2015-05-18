package com.td.service.lock;

import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.lock.ILockable;
import com.td.service.crud.CRUDService;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface LockService extends CRUDService {

    public void lock(ILockable lockable) throws LockException;

    public void unlock(ILockable lockable) throws LockException;

    public boolean isLockedByUser(ILockable lockable, IUserModel userModel);

    public boolean isLocked(ILockable lockable);
}
