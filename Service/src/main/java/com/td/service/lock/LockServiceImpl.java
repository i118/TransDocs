package com.td.service.lock;

import com.td.model.context.qualifier.LockQualifier;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.repository.IRepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.lock.ILock;
import com.td.model.entity.lock.ILockable;
import com.td.model.entity.lock.Lock;
import com.td.model.security.SecurityService;
import com.td.service.crud.AbstractCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
@Service
@LockQualifier
public class LockServiceImpl extends AbstractCRUDService implements LockService {

    private SecurityService securityService;


    @Autowired
    public LockServiceImpl(@LockQualifier IRepository<Lock> dao) {
        super(dao);
    }

    @Override
    public void lock(ILockable lockable) throws LockException {
        ILock lock = lockable.getLockObject();
        IUserModel userModel = getSecurityService().getCurrentUser();
        if(lock!=null) {
            if(!userModel.getLogin().equals(lock.getLockOwner())){
                throw new LockException("Объект заблокирован пользователем "+userModel.getLogin());
            }
        }else{
           lock = new Lock();
           lock.setLockOwner(userModel.getLogin());
           lock.setLockedObjectId(lockable.getObjectId());
           lockable.setLockObject(lock);
           repository.saveOrUpdate(lock);
        }
    }

    @Override
    public void unlock(ILockable lockable) throws LockException {
       ILock lock = lockable.getLockObject();
       if(lock!=null){
           IUserModel userModel = getSecurityService().getCurrentUser();
           if(!userModel.getLogin().equals(lock.getLockOwner())){
               throw new LockException("Объект заблокирован пользователем "+userModel.getLogin());
           }
         lockable.setLockObject(null);
         repository.delete(lock);
       }
    }

    @Override
    public boolean isLockedByUser(ILockable lockable, IUserModel userModel) {
        ILock lock = lockable.getLockObject();
        if(lock==null)return false;
        return lock.getLockOwner().equals(userModel.getLogin());
    }

    @Override
    public boolean isLocked(ILockable lockable) {
        ILock lock = lockable.getLockObject();
        return lock!=null;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    @Inject
    @SecurityQualifier
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
