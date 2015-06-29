package com.td.service.crud.file;

import com.td.model.repository.file.FileRepository;
import com.td.model.entity.file.IFileContainer;
import com.td.model.entity.file.IFileModel;
import com.td.service.lock.LockException;
import com.td.service.crud.CRUDService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 10.09.14.
 */
public interface FileService extends CRUDService<IFileModel> {

    public IFileModel getFileByPath(String path);

    public IFileModel getFileByPath(String path, Class<? extends IFileModel> clazz);

    public void saveFile(IFileModel fileModel, UUID containerId);

    public void deleteFile(UUID id);

    public IFileModel checkout(UUID id) throws LockException;

    public IFileModel checkIn(UUID id, InputStream content) throws IOException, LockException;

    public IFileModel getFile(UUID id) throws IOException;

    public IFileModel cancelCheckout(UUID id) throws LockException;

    public void saveFiles(IFileContainer fileContainer);

    public IFileModel createFile(String fileType);
}
