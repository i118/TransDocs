package com.td.service.permit;

import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.security.UserDetailsImpl;
import com.td.service.permit.user.EditUserPermitAction;
import com.td.service.crud.dictionary.role.RoleCRUDService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 * Created by konstantinchipunov on 04.08.14.
 */
public class EditUserPermitActionTest {

    @InjectMocks
    EditUserPermitAction permitAction;

    @Mock
    RoleCRUDService roleTransactService;

    @BeforeTest
    public void before() {
        initMocks(this);
        Mockito.reset(roleTransactService);
    }

    @Test
    public void testHasPermission(){
        RoleModel adminRole = new RoleModel();
        adminRole.setRoleName(RoleNames.ROLE_ADMIN);


        UserModel admin = new UserModel();
        admin.addRoleModel(adminRole);


        UserDetailsImpl details = mock(UserDetailsImpl.class);
        when(details.getCurrentUser()).thenReturn(admin);

        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(details);

        when(roleTransactService.hasRole(details.getCurrentUser(), RoleNames.ROLE_ADMIN)).thenReturn(true);
        when(roleTransactService.hasAnyRole(details.getCurrentUser(), RoleNames.ROLE_ADMIN, RoleNames.ROLE_SUPER_ADMIN)).thenReturn(true);
        assertTrue(permitAction.hasPermission(auth, null, EditUserPermitAction.ACTION_NAME));

        Mockito.reset(roleTransactService);
        Mockito.reset(details);

        RoleModel managerRole = new RoleModel();
        managerRole.setRoleName(RoleNames.ROLE_MANAGER);

        UserModel manager = new UserModel();
        manager.addRoleModel(managerRole);

        when(details.getCurrentUser()).thenReturn(manager);

        when(roleTransactService.hasRole(details.getCurrentUser(), RoleNames.ROLE_ADMIN)).thenReturn(false);
        assertFalse(permitAction.hasPermission(auth, null, EditUserPermitAction.ACTION_NAME));
    }

}
