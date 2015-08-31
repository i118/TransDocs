package com.td.model.dto.dictionary;

import com.td.model.dto.DirtySupportDTO;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.user.UserDTO;

/**
 * Created by zerotul.
 */
public abstract class DictionaryDTO extends ModelDTO {

    private UserDTO owner;

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
