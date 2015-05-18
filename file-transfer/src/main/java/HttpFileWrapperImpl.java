import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Created by konstantinchipunov on 06.10.14.
 */
public class HttpFileWrapperImpl implements IFileWrapper {

    private String fileName;

    private String extension;

    private String path;

    private InputStream content;

    private String id;

    private IFileLauncher launcher;

    private String checkInPath;

    private String cancelCheckoutPath;

    private String sessionId;

    public static final String JSESSIONID = "JSESSIONID";

    public static final String FILE_PATCH_PROPERTY = "filePatch";

    private HttpFileWrapperImpl(){}

    @Override
    public String getName() {
        return fileName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public InputStream getContent() {
        return content;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void edit() throws IOException{
      if(launcher==null) throw new IllegalStateException("file launcher not found");
      File file = getFile();
      System.out.println("file path = "+file.getCanonicalPath());
      launcher.launch(file);
    }

    @Override
    public void view() throws IOException{
      if(launcher==null) throw new IllegalStateException("file launcher not found");
      Path file = Files.createTempFile("transdocs", extension);
        System.out.println("file path = "+file.toAbsolutePath());
        OutputStream outputStream = null;
        InputStream content = null;
        try {
            outputStream = new FileOutputStream(file.toFile());
            content = getContent();
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = content.read(buffer)) > 0) {
                outputStream.write(buffer, 0, i);
            }
        }finally {
            if(outputStream!=null)outputStream.close();
            if(content!=null)content.close();
        }
      launcher.launch(file.toFile());
    }



    @Override
    public void save() throws IOException {
        HttpPost put = new HttpPost(checkInPath);
        System.out.println(checkInPath);
        File file = getLocalFile();
        System.out.println("file path = "+file.getCanonicalPath());
        HttpEntity entity = MultipartEntityBuilder.create()
                .addBinaryBody("file", file).build();
        put.setEntity(entity);
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(JSESSIONID, sessionId);
        cookieStore.addCookie(cookie);
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            HttpResponse putResponse = httpClient.execute(put);
            System.out.println(putResponse.getStatusLine());
            if (putResponse.getStatusLine().getStatusCode() == 200) {
                delete();
            }
        }finally {
            if(httpClient!=null)httpClient.close();
        }
    }

    public void cancelCheckout() throws IOException {
        HttpGet get = new HttpGet(cancelCheckoutPath);
        System.out.println(cancelCheckoutPath);
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(JSESSIONID, sessionId);
        cookieStore.addCookie(cookie);
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            HttpResponse putResponse = httpClient.execute(get);
            System.out.println(putResponse.getStatusLine());
            if (putResponse.getStatusLine().getStatusCode() == 200) {
                delete();
            }
        }finally {
            if(httpClient!=null)httpClient.close();
        }
    }

    public void delete() {
      try {
          File file = getLocalFile();

          if (!file.exists()) return;
          File parentDir = file.getParentFile();
          for (File child : parentDir.listFiles()) {
              child.delete();
          }
          parentDir.delete();
      }catch (Throwable e){
          e.printStackTrace();
      }
    }

    private File getLocalFile() throws IOException {
        File tdDir = DirUtil.getBaseDir();
        File file = new File(tdDir.getCanonicalPath() + File.separator + getId() + File.separator + getName());
        if (!file.exists()) {
            throw new IllegalStateException("file not found, filePath=" + file.getCanonicalPath());
        }
        return file;
    }

    private File getFile() throws IOException {
        File tdDir = DirUtil.getBaseDir();
        File fileDir = new File(tdDir, getId());
        if (!fileDir.exists()) fileDir.mkdirs();
        File file = new File(fileDir, getName());
        if(!file.exists()){
            file.createNewFile();
            OutputStream outputStream = null;
            InputStream content = null;
            try {
                outputStream = new FileOutputStream(file);
                content = getContent();
                byte[] buffer = new byte[1024];
                int i = 0;
                while ((i = content.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, i);
                }
            }finally {
                if(outputStream!=null)outputStream.close();
                if(content!=null)content.close();
            }
        }
        File fileProperty = new File(fileDir, getId()+".properties");
        fileProperty.createNewFile();
        Properties fileProperties = new Properties();
        OutputStream out = null;
        try{
           out =  new FileOutputStream(fileProperty);
           fileProperties.setProperty(FILE_PATCH_PROPERTY, file.getCanonicalPath());
           fileProperties.store(out, null);
        }finally {
            if(out!=null)out.close();
        }
        return file;
    }

    public static class FileInfoBuilder{
        private String fileName;

        private String extension;

        private String path;

        private InputStream content;

        private String id;

        private IFileLauncher launcher;

        private String checkInPath;

        private String cancelCheckoutPath;

        private String sessionId;

        private FileInfoBuilder(){}

        public static FileInfoBuilder with(){
            FileInfoBuilder builder = new FileInfoBuilder();
            return builder;
        }

        public FileInfoBuilder setName(String name){
            this.fileName = name;
            return this;
        }

        public FileInfoBuilder setExtension(String extension){
           this.extension = extension;
           return this;
        }

        public FileInfoBuilder setContent(InputStream content) {
            this.content = content;
            return this;
        }

        public FileInfoBuilder setPath(String path){
            this.path = path;
            return this;
        }

        public FileInfoBuilder setId(String id){
            this.id= id;
            return this;
        }

        public FileInfoBuilder setSessionId(String sessionId){
            this.sessionId= sessionId;
            return this;
        }

        public FileInfoBuilder setCheckInPath(String path){
            this.checkInPath = path;
            return this;
        }

        public FileInfoBuilder setCancelCheckoutPath(String path){
            this.cancelCheckoutPath = path;
            return this;
        }

        public FileInfoBuilder setLauncher(IFileLauncher launcher) {
            this.launcher = launcher;
            return this;
        }

        public IFileWrapper build(){
            HttpFileWrapperImpl fileInfo = new HttpFileWrapperImpl();
            fileInfo.fileName = this.fileName;
            fileInfo.extension= this.extension;
            fileInfo.path=this.path;
            fileInfo.content=this.content;
            fileInfo.id=this.id;
            fileInfo.launcher = this.launcher;
            fileInfo.checkInPath = this.checkInPath;
            fileInfo.sessionId = this.sessionId;
            fileInfo.cancelCheckoutPath = this.cancelCheckoutPath;
            return fileInfo;
        }
    }
}
