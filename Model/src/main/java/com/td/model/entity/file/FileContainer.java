package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CustomerModel;

import java.util.List;

/**
 * Created by konstantinchipunov on 16.09.14.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierFileModel.class, name = CarrierFileModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CustomerFileModel.class, name = CustomerFileModel.TABLE_NAME),
        @JsonSubTypes.Type(value = CarrierModel.class, name = CarrierModel.TABLE_NAME),
})
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType" , visible = true)
public interface FileContainer extends Persistent {

    @JsonManagedReference("file-container")
    public List<IFileModel> getFiles();


    public void setFiles(List<IFileModel> files);

    public void addFile(IFileModel fileModel);
}
