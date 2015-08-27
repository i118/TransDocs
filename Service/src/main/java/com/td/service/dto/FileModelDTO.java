package com.td.service.dto;

import com.td.model.entity.file.FileContainer;
import com.td.model.entity.file.IFileModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class FileModelDTO extends AbstractModelDTO implements IFileModel {

    private String name;

    private String fileLocation;

    private String mimeType;

    private FileType fileType;

    private String extension;

    private InputStream content;

    private FileContainer container;


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

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    public FileContainer getContainer() {
        return container;
    }

    public void setContainer(FileContainer container) {
        this.container = container;
    }

    public long getSize() {
        try {
            if (getContent() != null) return getContent().available();
        } catch (IOException e) {
            return -1l;
        }
      return 0l;
    }

    public void readContent(OutputStream out) {

    }

}
