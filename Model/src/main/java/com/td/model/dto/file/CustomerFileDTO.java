package com.td.model.dto.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.dictionary.contractor.CustomerDTO;
import com.td.model.entity.dictionary.company.ICustomerModel;
import com.td.model.entity.file.CustomerFileModel;

import java.util.List;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = CustomerFileModel.class)
public class CustomerFileDTO extends FileDTO {


    private static final long serialVersionUID = 2874336725246364466L;
    private CustomerFileDTO container;

    private List<CustomerFileDTO> files;

    private CustomerDTO owner;

    @JsonBackReference("fileStore")
    public CustomerDTO getOwner() {
        return owner;
    }

    public void setOwner(CustomerDTO owner) {
        this.owner = owner;
    }

    @JsonManagedReference("file-container")
    public List<CustomerFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<CustomerFileDTO> files) {
        this.files = files;
    }

    @JsonBackReference("file-container")
    public CustomerFileDTO getContainer() {
        return container;
    }

    public void setContainer(CustomerFileDTO container) {
        this.container = container;
    }
}
