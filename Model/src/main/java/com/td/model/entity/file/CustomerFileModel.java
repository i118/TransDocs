package com.td.model.entity.file;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.AbstractModel;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.ICustomerModel;
import com.td.model.listener.AttachmentListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by konstantinchipunov on 16.09.14.
 */
@Entity
@JsonAutoDetect
@JsonTypeName(CustomerFileModel.TABLE_NAME)
@Table(name = CustomerFileModel.TABLE_NAME)
@PrimaryKeyJoinColumn(name= AbstractModel.Columns.OBJECT_ID, referencedColumnName= AbstractModel.Columns.OBJECT_ID)
@EntityListeners({AttachmentListener.class})
public class CustomerFileModel extends FileModel implements IAttachment<ICustomerModel> {

    public static final String TABLE_NAME = "td_customer_file";

    private ICustomerModel owner;

    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    @JsonBackReference("fileStore")
    @ManyToOne(optional=true, targetEntity = CustomerModel.class)
    public ICustomerModel getOwner() {
        return owner;
    }

    @Override
    public void setOwner(ICustomerModel owner) {
        this.owner = owner;
    }

    @Override
    public void addFile(IFileModel fileModel) {
        super.addFile(fileModel);
        if(fileModel instanceof IAttachment){
            ((IAttachment) fileModel).setOwner(getOwner());
        }
    }
}
