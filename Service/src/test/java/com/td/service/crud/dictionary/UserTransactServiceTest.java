package com.td.service.crud.dictionary;

import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.dataset.UserDataSet;
import com.td.model.entity.dictionary.user.IPassword;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.security.SecurityService;
import com.td.model.utils.PagingArrayList;
import com.td.model.utils.PagingList;
import com.td.service.crud.LazyInitVisiter;
import com.td.service.crud.dictionary.role.RoleService;
import com.td.service.crud.dictionary.user.UserCRUDService;
import com.td.service.crud.dictionary.user.UserCRUDServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.zerotul.specification.Specification;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;
import static org.zerotul.specification.Specifications.from;

/**
 * Created by konstantinchipunov on 30.07.14.
 */
public class UserTransactServiceTest {


    @Mock
    UserRepository userModelDao;

    @Mock
    RoleService roleService;

    @Mock
    SecurityService securityService;

    @InjectMocks
    UserCRUDServiceImpl userTransactService;


    @BeforeTest
    public void before() {
        initMocks(this);
        Mockito.reset(userModelDao);
        Mockito.reset(roleService);
        Mockito.reset(securityService);
        userTransactService.setSecurityService(securityService);
        userTransactService.setRoleService(roleService);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.CREATE_USER_DATA
    )
    public void testCreateUser(UserModel user, String password) throws Exception {
        when(securityService.getCurrentUser()).thenReturn(user);
        Map args = spy(new HashMap<>());
        args.put(UserCRUDServiceImpl.ArgumentName.PASSWORD, password);
        userTransactService.createDictionaryObject((UserModel) user, args);

        verify(userModelDao).saveOrUpdate((UserModel) user);
        assertNotNull(user.getPassword());
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.USER_DATA
    )
    public void testGetUserByName(UserModel model) throws Exception {
        when(userModelDao.getUserByName(model.getLogin())).thenReturn(model);
        IUserModel userById = userTransactService.getUserByName(model.getLogin());

        assertEquals(model, userById);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.USER_DATA
    )
    public void testGetUserByNameVisiter(UserModel model) throws Exception {
        LazyInitVisiter visiter = mock(LazyInitVisiter.class);
        when(userModelDao.getUserByName(model.getLogin())).thenReturn(model);
        IUserModel userById = userTransactService.getUserByName(model.getLogin(), visiter);
        verify(visiter).initLazy(model);
        assertEquals(model, userById);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.CREATE_USER_DATA
    )
    public void testCreateDictionary(UserModel user, String password) throws Exception {
        when(securityService.getCurrentUser()).thenReturn(user);
        Map args = spy(new HashMap<>());
        args.put(UserCRUDServiceImpl.ArgumentName.PASSWORD, password);
        userTransactService.createDictionaryObject((UserModel) user, args);

        verify(userModelDao).saveOrUpdate((UserModel) user);
        verify(args).get(UserCRUDServiceImpl.ArgumentName.PASSWORD);
        assertNotNull(user.getPassword());
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.USER_DATA
    )
    public void testDeleteDictionary(IUserModel user) throws Exception {
        Map args = new HashMap<>();
        userTransactService.deleteDictionaryObject((UserModel) user, args);

        verify(userModelDao).delete((UserModel) user);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.USER_DATA
    )
    public void testDeleteUser(IUserModel user) throws Exception {
        userTransactService.deleteDictionaryObject((UserModel) user, new HashMap<>());

        verify(userModelDao).delete((UserModel) user);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.CREATE_USER_DATA
    )
    public void testUpdateUser(IUserModel user, String password) throws Exception {
        when(userModelDao.update((UserModel) user)).thenReturn((UserModel) user);
        Map args = spy(new HashMap<>());
        args.put(UserCRUDServiceImpl.ArgumentName.PASSWORD, password);
        userTransactService.updateDictionaryObject((UserModel) user, args);

        if (password != null) {
            assertNotNull(user.getPassword());
        }
        verify(userModelDao).saveOrUpdate((UserModel) user);

        Mockito.reset(userModelDao);
        Mockito.reset(args);
        when(userModelDao.update((UserModel) user)).thenReturn((UserModel) user);
        args.remove(UserCRUDServiceImpl.ArgumentName.PASSWORD);
        IPassword oldPassword = user.getPassword();
        userTransactService.updateDictionaryObject((UserModel) user, args);
        verify(userModelDao).saveOrUpdate((UserModel) user);
        assertEquals(user.getPassword(), oldPassword);
    }

    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.CREATE_USER_DATA
    )
    public void testUpdateDictionary(IUserModel user, String password) throws Exception {
        UserCRUDService userService = spy(userTransactService);
        Map args = new HashMap<>();
        userService.updateDictionaryObject((UserModel) user, args);
        verify(userService).updateDictionaryObject((UserModel) user, args);
    }


    @Test(
            dataProviderClass = UserDataProvider.class,
            dataProvider = UserDataProvider.DataProviders.USER_DATA_LIST
    )
    public void testGetAll(List<IUserModel> userModels) throws Exception {
        Specification<UserDataSet> specification = from(UserDataSet.class).endFrom();
        PagingList<UserDataSet> list = new PagingArrayList<>(Arrays.asList(mock(UserDataSet.class)), 1, 1);
        when(userModelDao.findDataSet(specification)).thenReturn(list);
        List<UserDataSet> users = userTransactService.findDataSet(specification);

        users.parallelStream().forEach((UserDataSet user)->{
            if(!(user instanceof UserDataSet)){
                fail();
            }
        });
    }




}
