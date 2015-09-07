package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.listener.AttachmentListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
@Entity
@JsonAutoDetect
@JsonTypeName(CarrierFileModel.TABLE_NAME)
@Table(name = CarrierFileModel.TABLE_NAME)
@PrimaryKeyJoinColumn(name= AbstractModel.Columns.OBJECT_ID, referencedColumnName= AbstractModel.Columns.OBJECT_ID)
@EntityListeners({AttachmentListener.class})
public class CarrierFileModel extends FileModel implements Attachment<CarrierModel> {

    public static final String TABLE_NAME = "td_carrier_file";

    private CarrierModel owner;

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    @JsonBackReference("fileStore")
    @ManyToOne(optional=true, targetEntity = CarrierModel.class)
    public CarrierModel getOwner() {
        return owner;
    }

    @Override
    public void setOwner(CarrierModel owner) {
        this.owner = owner;
    }

    @Override
    public void addFile(FileModel fileModel) {
        super.addFile(fileModel);
        if(fileModel instanceof Attachment){
            ((Attachment) fileModel).setOwner(getOwner());
        }
    }
}
