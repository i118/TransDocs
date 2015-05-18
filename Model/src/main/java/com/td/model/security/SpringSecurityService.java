package com.td.model.security;

import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by konstantinchipunov on 07.10.14.
 */
public class SpringSecurityService implements SecurityService {

    @Override
    public UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetailsImpl) {
                return ((UserDetailsImpl) principal).getCurrentUser();
            }
        }
        throw new IllegalStateException("Current user not found");
    }
}
