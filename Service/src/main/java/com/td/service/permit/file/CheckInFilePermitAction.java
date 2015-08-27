package com.td.service.permit.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.context.qualifier.LockQualifier;
import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.entity.file.IFileModel;
import com.td.model.entity.lock.Lockable;
import com.td.service.lock.LockService;
import com.td.service.permit.AbstractPermitAction;
import com.td.model.security.SecurityService;
import com.td.service.crud.file.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class CheckInFilePermitAction extends AbstractPermitAction<IFileModel> {

    public static final String ACTION_NAME = "CheckInFile";

    private SecurityService securityService;

    private LockService lockService;

    private FileService fileService;

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

    @Override
    @Transactional
    public boolean hasPermission(Authentication authentication, IFileModel targetDomainObject, Object permission) {
        if(!(targetDomainObject instanceof Lockable)) return false;
        if(!IFileModel.FileType.FILE.equals(targetDomainObject.getFileType())) return false;
        IFileModel fileModel = getFileService().findById(targetDomainObject.getObjectId());
        return lockService.isLockedByUser((Lockable) fileModel, getSecurityService().getCurrentUser());
    }

    @Override
    @Transactional
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
       if(targetId==null)return false;
       IFileModel fileModel = getFileService().findById((UUID) targetId);
       if(!(fileModel instanceof Lockable)) return false;
        if(!IFileModel.FileType.FILE.equals(fileModel.getFileType())) return false;
       return getLockService().isLockedByUser((Lockable) fileModel, getSecurityService().getCurrentUser());
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    @Inject
    @SecurityQualifier
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
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
