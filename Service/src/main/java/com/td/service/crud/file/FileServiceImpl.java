package com.td.service.crud.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.context.qualifier.LockQualifier;
import com.td.model.repository.file.FileRepository;
import com.td.model.entity.file.IFileContainer;
import com.td.model.entity.file.IFileModel;
import com.td.model.entity.lock.ILockable;
import com.td.service.dto.FileModelDTO;
import com.td.service.lock.LockException;
import com.td.service.lock.LockService;
import com.td.service.crud.AbstractCRUDService;
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
public class FileServiceImpl extends AbstractCRUDService<IFileModel, FileRepository> implements FileService {

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
    public void saveFile(IFileModel fileModel, UUID containerId) {
        IFileModel savedFile = saveOrUpdate(fileModel);
        //TODO маленький костыльчик, после мерджа обнуляется контент и в листенере файл не обновляется, если найдется вариант по лучше убрать
        savedFile.setContent(fileModel.getContent());
    }

    @Override
    @Transactional
    public void deleteFile(UUID id){
       IFileModel fileModel = getModel(id);
       delete(fileModel);
    }

    @Transactional
    public IFileModel checkout(UUID id) throws LockException {
       IFileModel fileModel = getModel(id);
       getLockService().lock((ILockable) fileModel);
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
    public IFileModel cancelCheckout(UUID id) throws LockException {
       IFileModel fileModel = getModel(id);
       getLockService().unlock((ILockable) fileModel);
       saveOrUpdate(fileModel);
       return fileModel;
    }

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRED)
    public void saveFiles(IFileContainer fileContainer){
      Collection<IFileModel> newFiles = new HashSet<>();
      Collection<IFileModel> dirtyFiles = new HashSet<>();
      sortedFiles(fileContainer, newFiles, dirtyFiles);
      newFiles.stream().forEach((IFileModel fileModel)->saveOrUpdate(fileModel));
      dirtyFiles.stream().forEach((IFileModel fileModel)->saveOrUpdate(fileModel));
    }

    protected void sortedFiles(IFileContainer container, Collection<IFileModel> newFiles, Collection<IFileModel> dirtyFiles){
        Collection<IFileModel> files = container.getFiles();
        if(files==null)return;
        for (IFileModel fileModel : container.getFiles()) {
            if(fileModel instanceof IFileContainer){
                sortedFiles((IFileContainer) fileModel, newFiles, dirtyFiles);
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
       IFileModel fileModel = getModel(id);

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
    public IFileModel checkIn(UUID id, InputStream content) throws IOException, LockException {
        IFileModel fileModel = getModel(id);
        getLockService().unlock((ILockable) fileModel);
        fileModel.setContent(content);
        fileModel.setModifyDate(new Date());
        saveFile(fileModel, null);

        return fileModel;
    }

    public IFileModel createFile(String fileType){
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

}
