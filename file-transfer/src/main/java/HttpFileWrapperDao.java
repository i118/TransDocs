import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class HttpFileWrapperDao implements IFileWrapperDao {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    public static final String ATTACHMENT = "attachment";

    public static final String FILE_PATH = "filepath";

    public static final String FILE_NAME = "filename";


    private String host;

    private String fileBaseUrl;

    private String sessionId;

    public static final String JSESSIONID = "JSESSIONID";

    private HttpFileWrapperDao(){}

    @Override
    public IFileWrapper getById(String fileId) {
        try {
            return requestFile(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected IFileWrapper requestFile(String fileId) throws IOException {
        String url = buildFileUrl(fileId);
        System.out.println(url);
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie(JSESSIONID, sessionId);
        cookieStore.addCookie(cookie);
        CloseableHttpClient httpClient = null;
        try {
             httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            HttpGet httpget = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(httpget);
            System.out.println(response.getStatusLine());
            return buildFileWrapper(response, fileId);
        }finally {
            if(httpClient!=null)httpClient.close();
        }
    }

    protected IFileWrapper buildFileWrapper(HttpResponse response, String fileId) throws IOException {
        Header[] headers = response.getHeaders(CONTENT_DISPOSITION);
        HttpFileWrapperImpl.FileInfoBuilder builder = HttpFileWrapperImpl.FileInfoBuilder.with();
        builder.setId(fileId);
        if (headers != null && headers.length == 1) {
            Header header = headers[0];
            HeaderElement attachmentElement = null;
            for (HeaderElement element : header.getElements()) {
                if (ATTACHMENT.equals(element.getName().toLowerCase())) {
                    attachmentElement = element;
                    break;
                }
            }
            NameValuePair fileName = attachmentElement.getParameterByName(FILE_NAME);
            if (fileName != null) {
                try {
                    String name = URLDecoder.decode(new String(fileName.getValue().getBytes("UTF-8")), "utf-8");
                    builder.setName(name);
                    builder.setExtension(getFileExtension(name));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException("file name not found");
            }
        }
        builder.setLauncher(new FileLauncherFactory().getLauncher());
        HttpEntity entity = response.getEntity();
        builder.setContent(getContent(entity));
        return builder.build();
    }

    protected String getFileExtension(String fileName){
       int index = fileName.lastIndexOf(".");
       if(index!=-1){
          return fileName.substring(index, fileName.length());
       }
       return "";
    }

    protected InputStream getContent(HttpEntity entity) throws IOException {
        Path file = Files.createTempFile("transdocs", "tmp");
        OutputStream outputStream =null;
        InputStream content = null;
        try {
            outputStream = new FileOutputStream(file.toFile());
            content = entity.getContent();
            byte[] buffer = new byte[1024];
            int i = 0;
            while ((i = content.read(buffer)) > 0) {
                outputStream.write(buffer, 0, i);
            }
        }finally {
            if(outputStream!=null)outputStream.close();
            if(content!=null)content.close();
        }
       return new FileInputStream(file.toFile());
    }

    protected String buildFileUrl(String fileId) {

        return host + fileBaseUrl + "/" + fileId + ";jsessionid=" + sessionId;
    }

    public static class HttpFileInfoDaoBuilder {

        private String host;

        private String fileBaseUrl;

        private String sessionId;

        private HttpFileInfoDaoBuilder(){}

        public static HttpFileInfoDaoBuilder create(){
            HttpFileInfoDaoBuilder builder = new HttpFileInfoDaoBuilder();
            return builder;
        }

        public HttpFileInfoDaoBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public HttpFileInfoDaoBuilder setFileBaseUrl(String fileBaseUrl) {
            this.fileBaseUrl = fileBaseUrl;
            return this;
        }

        public HttpFileInfoDaoBuilder setSessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public HttpFileWrapperDao build(){
            HttpFileWrapperDao dao = new HttpFileWrapperDao();
            dao.host = this.host;
            dao.fileBaseUrl = this.fileBaseUrl;
            dao.sessionId = this.sessionId;
            return dao;
        }
    }
}
