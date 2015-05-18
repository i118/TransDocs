package com.td.service.permit;

import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.crud.dictionary.role.RoleService;

import javax.inject.Inject;

/**
 * Created by konstantinchipunov on 03.08.14.
 */
public abstract class AbstractPermitAction<T extends Object> implements PermissionEvaluatorAdapter<T> {
    private RoleService roleService;

    protected boolean hasRole(IUserModel userModel, String roleName){
      return getRoleService().hasRole(userModel, roleName);
    }

    protected boolean hasAnyRole(IUserModel userModel, String... roleNames){
        return getRoleService().hasAnyRole(userModel, roleNames);
    }

    public RoleService getRoleService() {
        return roleService;
    }

    @Inject
    @RoleServiceQualifier
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public abstract String getActionName();
}
