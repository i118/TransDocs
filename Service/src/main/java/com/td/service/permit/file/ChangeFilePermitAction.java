package com.td.service.permit.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.context.qualifier.LockQualifier;
import com.td.model.entity.file.IFileModel;
import com.td.model.entity.lock.ILockable;
import com.td.service.lock.LockService;
import com.td.service.permit.AbstractPermitAction;
import com.td.service.crud.file.FileService;
import org.springframework.security.core.Authentication;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 15.10.14.
 */
public class ChangeFilePermitAction extends AbstractPermitAction<IFileModel> {

    public static final String ACTION_NAME = "ChangeFileAction";

    private LockService lockService;

    private FileService fileService;

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

    @Override
    public boolean hasPermission(Authentication authentication, IFileModel targetDomainObject, Object permission) {
        if(!(targetDomainObject instanceof ILockable)) return true;
        return !getLockService().isLocked((ILockable) targetDomainObject);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if(targetId==null)return false;
        IFileModel fileModel = getFileService().getModel((UUID) targetId);
        if(!(fileModel instanceof ILockable)) return true;
        return !getLockService().isLocked((ILockable) fileModel);
    }

    public LockService getLockService() {
        return lockService;
    }

    @Inject
    @LockQualifier
    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    public FileService getFileService() {
        return fileService;
    }

    @Inject
    @FileQualifier
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
