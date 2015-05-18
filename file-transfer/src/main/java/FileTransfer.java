import netscape.javascript.JSObject;

import java.applet.Applet;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Created by konstantinchipunov on 07.09.14.
 */
public class FileTransfer extends Applet {

    public static final String FILE_CONTENT_URL = "/FileController/checkout.file";
    public static final String VIEW_URL = "/FileController/get.content.file";

    private CheckoutPrivilegedAction checkoutActionPrototype;

    private CheckInPrivilegedAction checkInActionPrototype;

    private ViewFilePrivilegedAction viewFileActionPrototype;

    private CancelCheckoutPrivilegedAction cancelEditActionPrototype;

    private String sessionId;

    private String host;

    public void init() {
        sessionId = getParameter("sessionId");
        host = getParameter("host");
        checkoutActionPrototype = CheckoutPrivilegedAction.getPrototype(host, sessionId);
        viewFileActionPrototype = ViewFilePrivilegedAction.getPrototype(host, sessionId);
        checkInActionPrototype = CheckInPrivilegedAction.getPrototype(host, sessionId);
        cancelEditActionPrototype = CancelCheckoutPrivilegedAction.getPrototype(host, sessionId);
    }

    public void checkoutFile(final JSObject object)  {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String result = "result";
                try {
                    String fileId = (String) object.getMember("fileId");
                    if (fileId == null || "".equals(fileId)) {
                        result = "{result: 'error', message: 'file id can not be null'}";
                        return;
                    }
                    AccessController.doPrivileged(checkoutActionPrototype.makeCheckoutAction(fileId));
                    result = "{result: 'complete', message: 'все хорошо'}";
                }finally {
                    Object callback = object.getMember("callback");
                    System.out.println("callback class = " + callback.getClass());
                    if(callback instanceof JSObject) {
                        ((JSObject) callback).call("call", new Object[]{null,result});
                    }
                }

            }
        };
       Thread t = new Thread(runnable);
        t.start();
    }

    public void checkInFile(final JSObject object) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String result = "complete";
                try {
                    String fileId = (String) object.getMember("fileId");
                    if (fileId == null || "".equals(fileId)) result = "error";
                    OperationResult operationResult = AccessController.doPrivileged(checkInActionPrototype.makeCheckInAction(fileId));
                }finally {
                    Object callback = object.getMember("callback");
                    System.out.println("callback class = "+ callback.getClass());
                    if(callback instanceof JSObject) {
                        ((JSObject) callback).call("call", new Object[]{null,result});
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    public void viewFile(final JSObject object) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String result = "complete";
                try {
                    String fileId = (String) object.getMember("fileId");
                    if (fileId == null || "".equals(fileId)) result = "error";
                    OperationResult operationResult = AccessController.doPrivileged(viewFileActionPrototype.makeViewFileAction(fileId));
                }finally {
                    Object callback = object.getMember("callback");
                    System.out.println("callback class = "+ callback.getClass());
                    if(callback instanceof JSObject) {
                        ((JSObject) callback).call("call", new Object[]{null,result});
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    public void cancelEdit(final JSObject object) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
               String result = "complete";
               try {
                   String fileId = (String) object.getMember("fileId");
                   if (fileId == null || "".equals(fileId)) result = "error";
                   OperationResult operationResult = AccessController.doPrivileged(cancelEditActionPrototype.makeCancelCheckout(fileId));
               }finally {
                   Object callback = object.getMember("callback");
                   if (callback instanceof JSObject) {
                       ((JSObject) callback).call("call", new Object[]{null, result});
                   }
               }
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }



    private static class CheckoutPrivilegedAction implements PrivilegedAction<OperationResult>, Cloneable{

        private String fileId;

        private IFileWrapperDao dao;

        private IFileService fileService;

        public static CheckoutPrivilegedAction getPrototype(String host, String sessionId){
            CheckoutPrivilegedAction prototype = new CheckoutPrivilegedAction();
            prototype.dao = HttpFileWrapperDao.HttpFileInfoDaoBuilder.create()
                    .setHost(host)
                    .setFileBaseUrl(FILE_CONTENT_URL)
                    .setSessionId(sessionId).build();
            prototype.fileService = new FileServiceImpl(prototype.dao);
            return prototype;
        }

        public CheckoutPrivilegedAction makeCheckoutAction(String fileId){
            CheckoutPrivilegedAction action = clone();
            action.fileId = fileId;
            return action;
        }

        @Override
        public OperationResult run() {
            return fileService.checkoutFile(fileId);
        }

        @Override
        public CheckoutPrivilegedAction clone(){
           CheckoutPrivilegedAction copy = new CheckoutPrivilegedAction();
           copy.fileService = fileService;
           copy.dao = dao;
           return copy;
        }
    }

    public static class CheckInPrivilegedAction implements PrivilegedAction<OperationResult>, Cloneable{

        private String fileId;

        private IFileWrapperDao dao;

        private IFileService fileService;



        @Override
        public OperationResult run() {
            return fileService.checkInFile(fileId);
        }

        public static CheckInPrivilegedAction getPrototype(String host, String sessionId){
            CheckInPrivilegedAction prototype = new CheckInPrivilegedAction();
            prototype.dao = new FileSystemFileWrapperDao(host + "/FileController/checkIn.file", host + "/FileController/cancelCheckout.file", sessionId);
            prototype.fileService = new FileServiceImpl(prototype.dao);
            return prototype;
        }

        public CheckInPrivilegedAction makeCheckInAction(String fileId){
            CheckInPrivilegedAction action = clone();
            action.fileId = fileId;
            return action;
        }

        @Override
        public CheckInPrivilegedAction clone(){
            CheckInPrivilegedAction copy = new CheckInPrivilegedAction();
            copy.fileService = fileService;
            copy.dao = dao;
            return copy;
        }
    }

    public static class ViewFilePrivilegedAction implements PrivilegedAction<OperationResult>, Cloneable{

        private String fileId;

        private IFileWrapperDao dao;

        private IFileService fileService;



        @Override
        public OperationResult run() {
            fileService.vewFile(fileId);
            return OperationResult.COMPLETE;
        }

        public static ViewFilePrivilegedAction getPrototype(String host, String sessionId){
            ViewFilePrivilegedAction prototype = new ViewFilePrivilegedAction();
            prototype.dao = HttpFileWrapperDao.HttpFileInfoDaoBuilder.create()
                    .setHost(host)
                    .setFileBaseUrl(VIEW_URL)
                    .setSessionId(sessionId).build();
            prototype.fileService = new FileServiceImpl(prototype.dao);
            return prototype;
        }

        public ViewFilePrivilegedAction makeViewFileAction(String fileId){
            ViewFilePrivilegedAction action = clone();
            action.fileId = fileId;
            return action;
        }

        @Override
        public ViewFilePrivilegedAction clone(){
            ViewFilePrivilegedAction copy = new ViewFilePrivilegedAction();
            copy.fileService = fileService;
            copy.dao = dao;
            return copy;
        }
    }


    public static class CancelCheckoutPrivilegedAction implements PrivilegedAction<OperationResult>, Cloneable{

        private String fileId;

        private IFileWrapperDao dao;

        private IFileService fileService;

        @Override
        public OperationResult run() {
             fileService.cancelEdit(fileId);
            return null;
        }

        public static CancelCheckoutPrivilegedAction getPrototype(String host, String sessionId){
            CancelCheckoutPrivilegedAction prototype = new CancelCheckoutPrivilegedAction();
            prototype.dao = new FileSystemFileWrapperDao(host + "/FileController/checkIn.file", host + "/FileController/cancelCheckout.file", sessionId);
            prototype.fileService = new FileServiceImpl(prototype.dao);
            return prototype;
        }

        public CancelCheckoutPrivilegedAction makeCancelCheckout(String fileId){
            CancelCheckoutPrivilegedAction action = clone();
            action.fileId = fileId;
            return action;
        }

        @Override
        public CancelCheckoutPrivilegedAction clone(){
            CancelCheckoutPrivilegedAction copy = new CancelCheckoutPrivilegedAction();
            copy.fileService = fileService;
            copy.dao = dao;
            return copy;
        }
    }

}
