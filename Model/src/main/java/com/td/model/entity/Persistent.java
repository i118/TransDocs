package com.td.model.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.td.model.entity.dictionary.company.CarrierPersonModel;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.entity.file.CarrierFileModel;
import com.td.model.entity.file.CustomerFileModel;
import com.td.model.entity.file.FileModel;
import com.td.model.entity.lock.Lock;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 06.11.13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 *
 *
 * Базовый интерфейс модели
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.PROPERTY, property="objectType", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = UserModel.class, name = UserModel.TABLE_NAME),
                @JsonSubTypes.Type(value = RoleModel.class, name = RoleModel.TABLE_NAME),
                @JsonSubTypes.Type(value = CustomerModel.class, name = CustomerModel.TABLE_NAME),
                @JsonSubTypes.Type(value = CarrierPersonModel.class, name = CarrierPersonModel.TABLE_NAME),
                @JsonSubTypes.Type(value = CustomerFileModel.class, name = CustomerFileModel.TABLE_NAME),
                @JsonSubTypes.Type(value = CarrierFileModel.class, name = CarrierFileModel.TABLE_NAME),
                @JsonSubTypes.Type(value = FileModel.class, name = FileModel.TABLE_NAME),
                @JsonSubTypes.Type(value = DriverModel.class, name = DriverModel.TABLE_NAME),
                @JsonSubTypes.Type(value = Lock.class, name = Lock.TABLE_NAME)})
public interface Persistent extends Model {

    public void setObjectId(UUID value);

    public boolean isNew();

    public boolean isDeleted();

    public void setDeleted(boolean value);

    public String getTableName();

    public long getVersion();

    public Date getCreationDate();

    public void setCreationDate(Date date);

    public Date getModifyDate();

    public void setModifyDate(Date date);

    String getObjectType();

    void setObjectType(String objectType);
}
