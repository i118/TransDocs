package com.td.service.crud.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.context.qualifier.LockQualifier;
import com.td.model.entity.file.FileModel;
import com.td.model.repository.file.FileRepository;
import com.td.model.entity.file.FileContainer;
import com.td.model.entity.file.IFileModel;
import com.td.model.entity.lock.Lockable;
import com.td.service.dto.FileModelDTO;
import com.td.service.lock.LockException;
import com.td.service.lock.LockService;
import com.td.service.crud.GenericCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 10.09.14.
 */
@Service
@FileQualifier
public class FileServiceImpl extends GenericCRUDService<FileModel> implements FileService {

    private LockService lockService;

    @Inject
    public FileServiceImpl(@FileQualifier FileRepository dao) {
        super(dao);
    }

    @Override
    @Transactional
    public IFileModel getFileByPath(String path){
       return getRepository().getFileByPath(path);
    }

    public IFileModel getFileByPath(String path, Class<? extends IFileModel> clazz){
       return getRepository().getFileByPath(path, clazz);
    }

    @Override
    @Transactional
    public void saveFile(FileModel fileModel, UUID containerId) {
        save(fileModel);
    }

    @Override
    @Transactional
    public void deleteFile(UUID id){
       FileModel fileModel = findById(id);
       delete(fileModel);
    }

    @Transactional
    public IFileModel checkout(UUID id) throws LockException {
       FileModel fileModel = findById(id);
       getLockService().lock(fileModel);
       saveOrUpdate(fileModel);

       FileModelDTO dto = new FileModelDTO();
       dto.setContent(fileModel.getContent());
       dto.setName(fileModel.getName());
       dto.setObjectId(fileModel.getObjectId());
       dto.setFileLocation(fileModel.getFileLocation());
       dto.setMimeType(fileModel.getMimeType());
       dto.setExtension(fileModel.getExtension());
       return dto;
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public FileModel cancelCheckout(UUID id) throws LockException {
       FileModel fileModel = findById(id);
       getLockService().unlock((Lockable) fileModel);
       saveOrUpdate(fileModel);
       return fileModel;
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public void saveFiles(FileContainer fileContainer){
      Collection<FileModel> newFiles = new HashSet<>();
      Collection<FileModel> dirtyFiles = new HashSet<>();
      sortedFiles(fileContainer, newFiles, dirtyFiles);
      newFiles.stream().forEach((FileModel fileModel)->saveOrUpdate(fileModel));
      dirtyFiles.stream().forEach((FileModel fileModel)->saveOrUpdate(fileModel));
    }

    protected void sortedFiles(FileContainer container, Collection<FileModel> newFiles, Collection<FileModel> dirtyFiles){
        Collection<FileModel> files = container.getFiles();
        if(files==null)return;
        for (FileModel fileModel : container.getFiles()) {
            if(fileModel instanceof FileContainer){
                sortedFiles((FileContainer) fileModel, newFiles, dirtyFiles);
            }
            if(fileModel.isNew()){
                newFiles.add(fileModel);
            }else{
                dirtyFiles.add(fileModel);
            }
        }
    }

    @Transactional
    public IFileModel getFile(UUID id){
       IFileModel fileModel = findById(id);

       FileModelDTO dto = new FileModelDTO();
       dto.setContent(fileModel.getContent());
       dto.setName(fileModel.getName());
       dto.setObjectId(fileModel.getObjectId());
       dto.setFileLocation(fileModel.getFileLocation());
       dto.setMimeType(fileModel.getMimeType());
       dto.setExtension(fileModel.getExtension());
       return dto;
    }

    @Transactional
    public FileModel checkIn(UUID id, InputStream content) throws IOException, LockException {
        FileModel fileModel = findById(id);
        getLockService().unlock((Lockable) fileModel);
        fileModel.setContent(content);
        fileModel.setModifyDate(new Date());
        FileModel updatedFile = getRepository().update(fileModel);
        updatedFile.setContent(fileModel.getContent());
        return fileModel;
    }

    public FileModel createFile(String fileType){
       return getTypeFactory().createObjectByType(fileType);
    }

    public LockService getLockService() {
        return lockService;
    }

    @Inject
    @LockQualifier
    public void setLockService(LockService lockService) {
        this.lockService = lockService;
    }

    @Override
    protected FileRepository getRepository() {
        return (FileRepository) super.getRepository();
    }
}
