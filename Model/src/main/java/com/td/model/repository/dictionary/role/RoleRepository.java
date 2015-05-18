package com.td.model.repository.dictionary.role;

import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public interface RoleRepository extends DictionaryRepository<RoleModel>, IRepository<RoleModel> {

    public IRoleModel getRoleByName(String roleName);
}
