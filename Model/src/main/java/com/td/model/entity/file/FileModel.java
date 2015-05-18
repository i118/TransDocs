package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.jcr.JcrFactory;
import com.td.jcr.JcrOperations;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.lock.ILock;
import com.td.model.entity.lock.ILockable;
import com.td.model.entity.lock.Lock;
import com.td.model.listener.FileListener;

import javax.jcr.Node;
import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 09.09.14.
 */
@Entity
@JsonAutoDetect
@JsonTypeName(FileModel.TABLE_NAME)
@Table(name = FileModel.TABLE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"content","size"})
@EntityListeners({FileListener.class})
public class FileModel extends AbstractModel implements IFileModel, IFileContainer, ILockable {

    public static final String TABLE_NAME = "td_file";

    private String name;

    private String fileLocation;

    private String mimeType;

    private FileType fileType;

    private String extension;

    private transient InputStream content;

    private IFileContainer container;

    private List<IFileModel> files;

    private ILock lock;

    @Override
    @OneToOne(targetEntity = Lock.class, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    public ILock getLockObject() {
        return lock;
    }

    @Override
    public void setLockObject(ILock lock) {
       this.lock = lock;
    }

    public static class Columns extends AbstractModel.Columns{
        public static final String FILE_NAME= "file_name";
        public static final String FILE_LOCATION= "file_location";
        public static final String MIME_TYPE= "mime_type";
        public static final String FILE_TYPE = "file_type";
        public static final String EXTENSION = "extension";
    }

    @Override
    @Column(name = Columns.FILE_NAME)
    public String getName() {
        return name;
    }

    @Override
    public void setName(String fileName) {
        this.name = fileName;
    }

    @Override
    @JsonIgnoreProperties
    @Column(name = Columns.FILE_LOCATION)
    public String getFileLocation() {
        return fileLocation;
    }

    @Override
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    @Column(name = Columns.MIME_TYPE)
    public String getMimeType() {
        return mimeType;
    }

    @Override
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    @Column(name = Columns.FILE_TYPE)
    public FileType getFileType() {
        return fileType;
    }

    @Override
    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    @Override
    @Column(name = Columns.EXTENSION)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    @Transient
    public void setContent(InputStream in){
        this.content = in;
    }

    @Override
    @Transient
    public InputStream getContent() {
      if(content==null){
         return readFile();
      }
      return content;
    }

    @Override
    @Transient
    public void readContent(OutputStream out) {
        JcrOperations operations = JcrFactory.getInstance().getOperations();
        Node fileNode  = operations.getNodeByPath(getFileLocation());
        if(fileNode==null)throw new IllegalStateException("node not found, fileId = "+getObjectId().toString());
        operations.readFile(fileNode, out);
    }

    @Override
    @Transient
    public long getSize(){
        try {
            return getContent().available();
        } catch (IOException e) {
            return -1l;
        }
    }

    protected InputStream readFile(){
      JcrOperations operations = JcrFactory.getInstance().getOperations();
      Node fileNode  = operations.getNodeByPath(getFileLocation());
      if(fileNode==null)throw new IllegalStateException("node not found, fileId = "+getObjectId().toString());
      return operations.readFile(fileNode);
    }

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    public String toString(){
        return name;
    }

    @Override
    @OneToMany(
            mappedBy = "container",
            targetEntity = FileModel.class,
            cascade = {CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval=true
    )
    @JsonManagedReference("file-container")
    public List<IFileModel> getFiles(){
      return files;
    }

    public void setFiles(List<IFileModel> files){
        this.files = files;
    }

    public void addFile(IFileModel fileModel){
        if(files == null){
            files = new ArrayList<>();
        }
       files.add(fileModel);
       fileModel.setContainer(this);
    }

    @Override
    @ManyToOne(optional=true,
            targetEntity = FileModel.class
    )
    @JsonBackReference("file-container")
    public IFileContainer getContainer(){
      return this.container;
    }

    @Transient
    public UUID getContainerId(){
      return getContainer()!=null ? getContainer().getObjectId() : null;
    }

    public void setContainer(IFileContainer container){
        this.container = container;
    }
}
