package com.td.model.dto.dictionary.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.td.model.annotation.DTO;
import com.td.model.dto.ModelDTO;
import com.td.model.entity.dictionary.role.RoleModel;

/**
 * Created by zerotul.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@DTO(mappedBy = RoleModel.class)
public class RoleDTO extends ModelDTO {

    private static final long serialVersionUID = 8368968796515592951L;
    private String roleName;

    private String description;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
