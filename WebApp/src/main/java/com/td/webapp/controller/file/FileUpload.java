package com.td.webapp.controller.file;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by konstantinchipunov on 18.09.14.
 */
public class FileUpload {

    private CommonsMultipartFile file;

    public FileUpload(){
        super();
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
