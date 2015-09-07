package com.td.model.dto.file;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.dictionary.contractor.CarrierDTO;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.file.CarrierFileModel;

import java.util.List;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = CarrierFileModel.class)
public class CarrierFileDTO extends FileDTO{

    private static final long serialVersionUID = 2229140121014653884L;

    private CarrierDTO owner;

    private CarrierFileDTO container;

    private List<CarrierFileDTO> files;

    @JsonBackReference("fileStore")
    public CarrierDTO getOwner() {
        return owner;
    }

    public void setOwner(CarrierDTO owner) {
        this.owner = owner;
    }

    @JsonManagedReference("file-container")
    public List<CarrierFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<CarrierFileDTO> files) {
        this.files = files;
    }

    @JsonBackReference("file-container")
    public CarrierFileDTO getContainer() {
        return container;
    }

    public void setContainer(CarrierFileDTO container) {
        this.container = container;
    }
}
