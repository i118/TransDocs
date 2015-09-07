package com.td.model.dto.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.file.FileContainer;
import com.td.model.entity.file.FileModel;
import com.td.model.entity.file.IFileModel;
import com.td.model.entity.lock.ILock;

import java.io.InputStream;
import java.util.List;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = FileModel.class)
public class FileDTO extends ModelDTO{

    private String name;

    private String fileLocation;

    private String mimeType;

    private IFileModel.FileType fileType;

    private String extension;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public IFileModel.FileType getFileType() {
        return fileType;
    }

    public void setFileType(IFileModel.FileType fileType) {
        this.fileType = fileType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }



}
