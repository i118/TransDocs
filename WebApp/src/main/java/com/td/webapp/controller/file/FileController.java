package com.td.webapp.controller.file;

import com.td.model.context.qualifier.FileQualifier;
import com.td.model.entity.file.IFileContainer;
import com.td.model.entity.file.IFileModel;
import com.td.model.utils.StringUtil;
import com.td.service.lock.LockException;
import com.td.service.crud.file.FileService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 18.09.14.
 */
@Controller
@RequestMapping("/" + FileController.CONTROLLER_NAME)
public class FileController extends AbstractController {
    Logger logger = Logger.getLogger(FileController.class);

    public static final String CONTROLLER_NAME = "FileController";

    private FileService fileService;

    public static class RequestName extends AbstractController.RequestName {
        public static final String CREATE_FILE = "create.file";

        public static final String CHECK_IN_FILE = "checkIn.file";

        public static final String GET_FILES = "get.files";

        public static final String LOAD_FILE = "load.file";

        public static final String DELETE_FILE = "delete.file";

        public static final String CHECKOUT_FILE = "checkout.file";

        public static final String GET_CONTENT = "get.content.file";

        public static final String CANCEL_CHECKOUT = "cancelCheckout.file";
    }


    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    @RequestMapping(value = RequestName.CREATE_FILE, method = RequestMethod.POST)
    public
    @ResponseBody
    IResponse createFile(FileUpload uploadItem, @RequestParam String fileType, @RequestParam(required = false) String containerId) throws IOException {
        logger.debug("create file. fileName = " + uploadItem.getFile().getOriginalFilename());
        IFileModel fileModel = fileService.createFile(fileType);
        try (InputStream in = uploadItem.getFile().getInputStream()) {
            fileModel.setContent(in);
            fileModel.setName(uploadItem.getFile().getOriginalFilename());
            fileModel.setMimeType(uploadItem.getFile().getContentType());
            fileModel.setFileType(IFileModel.FileType.FILE);

            UUID id = StringUtil.hasText(containerId) ? UUID.fromString(containerId) : null;
            getFileService().saveFile(fileModel, id);
        }
        IResponse response = new ResponseImpl<>();
        response.addResult(fileModel);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value ="/" + RequestName.CHECK_IN_FILE+"/{fileId}", method = RequestMethod.POST)
    public
    @ResponseBody
    IResponse checkInFile( @PathVariable String fileId, FileUpload uploadItem) throws IOException, LockException {
        logger.debug("create file. fileName = " + uploadItem.getFile().getOriginalFilename());
        IFileModel fileModel = getFileService().checkIn(UUID.fromString(fileId), uploadItem.getFile().getInputStream());
        IResponse response = new ResponseImpl<>();
        response.addResult(fileModel);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.GET_FILES + "/{containerId}", method = RequestMethod.GET)
    public
    @ResponseBody
    IResponse getFiles(@PathVariable String containerId, @RequestParam String containerType) {
        IResponse response = new ResponseImpl<>();
        response.setSuccess(true);
        IFileContainer container = null;
        final List<IFileModel> files = new ArrayList<>();
        try {
            container = getFileService().getReference(UUID.fromString(containerId), containerType);
        } catch (IllegalArgumentException e) {
            logger.warn("container not found, containerId = " + containerId);
            response.addResults(files);
            return response;
        }

        getFileService().initLazy(container, (IFileContainer c) -> {
            c.getFiles().stream().forEach((IFileModel file) -> {
                files.add(file);
            });
        });

        response.addResults(files);
        return response;
    }

    @RequestMapping(value = "/"+RequestName.LOAD_FILE+"/{fileId}", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody IResponse loadFile(@PathVariable String fileId){
        IFileModel fileModel = getFileService().getModel(UUID.fromString(fileId));
        IResponse response = new ResponseImpl<>();
        response.addResult(fileModel);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.DELETE_FILE + "/{fileId}", method = RequestMethod.DELETE, headers = CONTENT_TYPE)
    public
    @ResponseBody
    IResponse deleteFiles(@PathVariable String fileId) {
        getFileService().deleteFile(UUID.fromString(fileId));
        IResponse response = new ResponseImpl<>();
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.CHECKOUT_FILE + "/{fileId}", method = RequestMethod.GET)
    public
    @ResponseBody
    HttpEntity<Resource> checkoutFile(@PathVariable String fileId) throws IOException, LockException {
        HttpEntity<Resource> entity = null;
        if (StringUtil.hasText(fileId)) {
            IFileModel fileModel = getFileService().checkout(UUID.fromString(fileId));
            entity=buildEntity(fileModel, null);
        }
        return entity;
    }

    @RequestMapping(value = "/" + RequestName.CANCEL_CHECKOUT+ "/{fileId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void cancelCheckoutFile(@PathVariable String fileId) throws IOException, LockException {
        if (StringUtil.hasText(fileId)) {
            getFileService().cancelCheckout(UUID.fromString(fileId));
        }
    }

    @RequestMapping(value = "/" + RequestName.GET_CONTENT + "/{fileId}", method = RequestMethod.GET)
    public
    @ResponseBody
    HttpEntity<Resource> getFileContent(@PathVariable String fileId, @RequestHeader("User-Agent") String userAgent) throws IOException, LockException {
        HttpEntity<Resource> entity = null;
        if (StringUtil.hasText(fileId)) {
            IFileModel fileModel = getFileService().getFile(UUID.fromString(fileId));
            entity=buildEntity(fileModel, userAgent);
        }
        return entity;
    }

    protected HttpEntity<Resource> buildEntity(IFileModel fileModel, String userAgent) throws UnsupportedEncodingException {
        String fileName = URLEncoder.encode(new String(fileModel.getName().getBytes("UTF-8")), String.valueOf(Charset.defaultCharset())).replaceAll("\\+", "%20");

        HttpHeaders header = new HttpHeaders();
        if(userAgent==null || userAgent.toLowerCase().indexOf("apache")!=-1){
            header.set(Header.CONTENT_DISPOSITION, "attachment; filename=" +fileName);
        }else {
            header.set(Header.CONTENT_DISPOSITION, "attachment; filename*=utf-8''" +fileName);
        }

        long length = fileModel.getSize();
        header.setContentLength(length);
        InputStreamResource resource = new SizeSupportInputStreamResource(fileModel.getContent(), length);
        String[] mimeType = fileModel.getMimeType() != null ? fileModel.getMimeType().split("/") : null;
        if (mimeType == null || mimeType.length != 2) {
            mimeType = new String[]{"application", "octet-stream"};
        }
        MediaType type = new MediaType(mimeType[0], mimeType[1], Charset.forName("ISO-8859-1"));
        header.setContentType(type);
        return  new HttpEntity<Resource>(resource, header);
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
