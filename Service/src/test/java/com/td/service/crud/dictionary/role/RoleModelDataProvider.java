package com.td.service.crud.dictionary.role;

import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import org.testng.annotations.DataProvider;

/**
 * Created by konstantinchipunov on 04.08.14.
 */
public class RoleModelDataProvider {

    public static class DataProviders{
        public static final String ROLE_MODEL_DATA = "roleModelData";
        public static final String HAS_ROLE_MODEL_DATA = "hasRoleModelData";
    }

    @DataProvider
    public static Object[][] roleModelData(){
        IRoleModel adminRole = new RoleModel();
        adminRole.setRoleName(RoleNames.ROLE_ADMIN);
        IRoleModel managerRole = new RoleModel();
        managerRole.setRoleName(RoleNames.ROLE_MANAGER);
        return new Object[][]{
                {adminRole},{managerRole}
        };
    }

    @DataProvider
    public static Object[][] hasRoleModelData(){
        IRoleModel adminRole = new RoleModel();
        adminRole.setRoleName(RoleNames.ROLE_ADMIN);
        IUserModel admin = new UserModel();
        admin.addRoleModel(adminRole);

        IRoleModel managerRole = new RoleModel();
        managerRole.setRoleName(RoleNames.ROLE_MANAGER);
        IUserModel manager = new UserModel();
        manager.addRoleModel(managerRole);
        return new Object[][]{
                {admin, RoleNames.ROLE_ADMIN, true},
                {admin, RoleNames.ROLE_MANAGER, false},
                {manager, RoleNames.ROLE_MANAGER, true},
                {manager, RoleNames.ROLE_ADMIN, false}
        };
    }
}
