package com.td.model.entity.dictionary.company;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.td.model.entity.file.CustomerFileModel;
import com.td.model.entity.file.IAttachment;
import com.td.model.entity.file.IFileModel;
import com.td.model.listener.ContractorListener;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by konstantinchipunov on 13.08.14.
 */
@Entity
@Table(name = CustomerModel.TABLE_NAME)
@JsonTypeName(CustomerModel.TABLE_NAME)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"files"})
@DiscriminatorValue(CustomerModel.TABLE_NAME)
@EntityListeners({ContractorListener.class})
public class CustomerModel extends JuridicalPersonModel implements ICustomerModel {

    public static final String TABLE_NAME = "td_customer";

    private Set<IContractPerson> persons;

    private IAttachment<ICustomerModel> fileStore;

    public static class Columns extends JuridicalPersonModel.Columns {

    }

    public static class Relations extends JuridicalPersonModel.Relations {

    }

    @Override
    @OneToMany(
            mappedBy = "contractor",
            targetEntity = CustomerPersonModel.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference("persons")
    public Set<IContractPerson> getPersons() {
        return persons;
    }

    public void setPersons(Set<IContractPerson> persons) {
        this.persons = persons;
    }

    @Override
    public void addPerson(IContractPerson person) {
        if (persons == null) {
            persons = new HashSet<>();
        }
        persons.add(person);
        person.setContractor(this);
    }


    @OneToOne(fetch = FetchType.EAGER, targetEntity = CustomerFileModel.class, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonManagedReference("fileStore")
    public IAttachment<ICustomerModel> getFileStore() {
        return fileStore;
    }

    public void setFileStore(IAttachment<ICustomerModel> fileStore) {
        this.fileStore = fileStore;
    }


    @Override
    @Transient
    public List<IFileModel> getFiles() {
        if (fileStore == null) {
            CustomerFileModel fileModel = new CustomerFileModel();
            fileModel.setOwner(this);
            fileModel.setName(fileModel.getObjectId().toString());
            fileModel.setMimeType("");
            fileModel.setExtension("");
            fileModel.setFileType(IFileModel.FileType.STORE);
            setFileStore(fileModel);
        }
        return getFileStore().getFiles();
    }

    @Override
    public void setFiles(List<IFileModel> files) {
        getFileStore().setFiles(files);
    }

    public void addFile(IFileModel fileModel) {
        getFileStore().addFile(fileModel);
    }


    @Override
    @Transient
    public String getTableName() {
        return TABLE_NAME;
    }

}
