package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by konstantinchipunov on 30.09.14.
 */
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = CustomerFileModel.class, name = CustomerFileModel.TABLE_NAME),
                @JsonSubTypes.Type(value = CarrierFileModel.class, name = CarrierFileModel.TABLE_NAME)
        }
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "objectType", visible = true)
public interface IAttachment<T extends IFileContainer> extends IFileModel, IFileContainer {

    @JsonBackReference("fileStore")
    public T getOwner();

    public void setOwner(T owner);
}
