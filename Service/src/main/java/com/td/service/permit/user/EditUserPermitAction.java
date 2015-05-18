package com.td.service.permit.user;

import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.security.UserDetailsImpl;
import com.td.service.permit.AbstractPermitAction;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Created by konstantinchipunov on 03.08.14.
 */
public class EditUserPermitAction extends AbstractPermitAction {

    public static final String ACTION_NAME = "EditUserAction";

    @Override
    public String getActionName() {
        return ACTION_NAME;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        IUserModel currentUser  = getCurrentUser(authentication);
       return hasAnyRole(currentUser, RoleNames.ROLE_ADMIN, RoleNames.ROLE_SUPER_ADMIN);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    protected IUserModel getCurrentUser(Authentication authentication){
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetailsImpl){
            return ((UserDetailsImpl)principal).getCurrentUser();
        }
       throw new IllegalStateException("Current user not found");
    }
}
