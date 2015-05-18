package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.td.model.entity.IPersistent;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by konstantinchipunov on 10.09.14.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerFileModel.class, name = CustomerFileModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierFileModel.class, name = CarrierFileModel.TABLE_NAME)
})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType" , visible = true)
public interface IFileModel extends IPersistent{

    public  String getName();

    public void setName(String fileName);

    public String getFileLocation();

    public  void setFileLocation(String fileLocation);

    public String getMimeType();

    public void setMimeType(String mimeType);

    public FileType getFileType();

    public void setFileType(FileType fileType);

    public String getExtension();

    public void setExtension(String extension);

    public void setContent(InputStream in);

    public InputStream getContent();

    public void readContent(OutputStream out);

    public long getSize();

    @JsonBackReference("file-container")
    public IFileContainer getContainer();

    public void setContainer(IFileContainer container);


    public enum FileType{
        FILE, FOLDER, STORE;

        @JsonCreator
        public static  FileType forValue(String value){
            try {
                return FileType.valueOf(value);
            }catch (IllegalArgumentException e){
                return null;
            }
        }

        @JsonValue
        public String toValue(){
            return   this.toString();
        }
    }
}
