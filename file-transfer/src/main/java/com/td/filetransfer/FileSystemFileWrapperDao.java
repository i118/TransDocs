package com.td.filetransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class FileSystemFileWrapperDao implements IFileWrapperDao {

    private String checkInPath;

    private String cancelCheckoutPath;

    //TODO не много не красиво иметь это здесь, подумать как избавится
    private String sessionId;

    public FileSystemFileWrapperDao(String checkInPath, String cancelCheckoutPath,String sessionId) {
        this.checkInPath = checkInPath;
        this.sessionId = sessionId;
        this.cancelCheckoutPath = cancelCheckoutPath;
    }

    @Override
    public IFileWrapper getById(String fileId) {
        try{
            File file = getFile(fileId);
            String checkInPath = this.checkInPath +"/" + fileId + ";jsessionid=" + sessionId;
            String cancelCheckoutPath = this.cancelCheckoutPath +"/" + fileId + ";jsessionid=" + sessionId;
            IFileWrapper fileInfo = HttpFileWrapperImpl.FileInfoBuilder.with()
                    .setName(file.getName())
                    .setPath(file.getPath())
                    .setId(fileId)
                    .setContent(new FileInputStream(file))
                    .setLauncher(new FileLauncherFactory().getLauncher())
                    .setCheckInPath(checkInPath)
                    .setSessionId(sessionId)
                    .setCancelCheckoutPath(cancelCheckoutPath)
                    .build();
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected File getFile(String fileId) throws IOException {
        File tdDir = DirUtil.getBaseDir();
        StringBuilder propertyFilePatch = new StringBuilder(tdDir.getCanonicalPath())
                .append(File.separator)
                .append(fileId)
                .append(File.separator)
                .append(fileId).append(".properties");
        File propertyFile = new File(propertyFilePatch.toString());
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(propertyFile);
            properties.load(in);
            String filePath = properties.getProperty(HttpFileWrapperImpl.FILE_PATCH_PROPERTY);
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IllegalStateException("file not found, filePath=" + file.getCanonicalPath());
            }
            return file;
        }finally {
            if(in!=null)in.close();
        }
    }
}
