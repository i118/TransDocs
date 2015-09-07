package com.td.model.repository.file;

import com.td.model.repository.IRepository;
import com.td.model.entity.file.FileModel;
import com.td.model.entity.file.IFileModel;

/**
 * Created by konstantinchipunov on 10.09.14.
 */
public interface FileRepository extends IRepository<FileModel> {

    public IFileModel getFileByPath(String path);

    public IFileModel getFileByPath(String path, Class<? extends IFileModel> clazz);
}
