package com.td.service.crud.dictionary.role;

import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

/**
 * Created by konstantinchipunov on 04.08.14.
 */
public class RoleTransactServiceTest {

    @InjectMocks
    private RoleServiceImpl roleTransactService;

    @Mock
    private RoleRepository roleModelDao;

    @BeforeTest
    public void before() {
        initMocks(this);
        Mockito.reset(roleModelDao);
    }

    @Test(dataProvider = RoleModelDataProvider.DataProviders.ROLE_MODEL_DATA, dataProviderClass = RoleModelDataProvider.class)
    public void testGetRoleByName(IRoleModel roleModel) {
        when(roleModelDao.getRoleByName(roleModel.getRoleName())).thenReturn(roleModel);
        IRoleModel roleByName = roleTransactService.getRoleByName(roleModel.getRoleName());
        assertEquals(roleModel, roleByName);
    }

    @Test(dataProvider = RoleModelDataProvider.DataProviders.HAS_ROLE_MODEL_DATA, dataProviderClass = RoleModelDataProvider.class)
    public void testHasRole(IUserModel userModel, String roleName, boolean hasRole) {
       boolean result = roleTransactService.hasRole(userModel, roleName);
       assertEquals(result, hasRole);
    }
}
