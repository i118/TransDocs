package com.td.model.repository.dictionary.role;

import com.td.model.repository.dictionary.IDataService;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.role.RoleNames;

/**
 * Created by konstantinchipunov on 29.07.14.
 */
public class RoleDataService implements IDataService<IRoleModel>{


    private static volatile RoleDataService dataService;

    private RoleDataService() {
    }

    public static RoleDataService getInstance(){
        if(dataService == null){
            synchronized (RoleDataService.class){
                if(dataService == null){
                    dataService = new RoleDataService();
                }
            }
        }
       return dataService;
    }


    @Override
    public IRoleModel[][] getDataArray() {
        IRoleModel adminRole = new RoleModel();
        adminRole.setRoleName(RoleNames.ROLE_ADMIN);
        adminRole.setDescription("administrator");
        IRoleModel managerRole = new RoleModel();
        managerRole.setRoleName(RoleNames.ROLE_MANAGER);
        managerRole.setDescription("manager");

        return new IRoleModel[][]{{managerRole}, {adminRole}};
    }
}
