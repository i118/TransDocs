package com.td.model.dto.dictionary.contractor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.td.model.annotation.DTO;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.user.UserModel;

import java.util.Set;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = CompanyModel.class)
public class CompanyDTO extends JuridicalPersonDTO {

    private static final long serialVersionUID = -4739759619021674572L;

    private Set<UserDTO> users;

    private String login;

    @JsonManagedReference("users")
    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
