package com.td.model.dto.dictionary.user;

import com.td.model.annotation.DTO;
import com.td.model.dto.dictionary.DictionaryDTO;
import com.td.model.entity.dictionary.user.UserModel;

/**
 * Created by zerotul.
 */
@DTO(mappedBy = UserModel.class)
public class UserDTO extends DictionaryDTO {
}
