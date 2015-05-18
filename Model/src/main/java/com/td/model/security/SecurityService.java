package com.td.model.security;

import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public interface SecurityService {

    public UserModel getCurrentUser();
}
