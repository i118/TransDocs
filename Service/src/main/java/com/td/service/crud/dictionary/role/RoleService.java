package com.td.service.crud.dictionary.role;

import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.service.crud.dictionary.DictionaryCRUDService;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public interface RoleService extends DictionaryCRUDService<RoleModel> {
    public RoleModel getRoleByName(String roleName);

    public boolean hasRole(UserModel userModel, String roleName);

    public boolean hasAnyRole(UserModel userModel, String... roleName);
}
