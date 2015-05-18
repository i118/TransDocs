package com.td.model.repository.dictionary.user;

import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.context.qualifier.UserQualifier;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

/**
 * Created by konstantinchipunov on 03.07.14.
 */
public class UserDaoTest extends AbstractDaoTest {

    @Autowired
    @UserQualifier
    UserRepository userModelDao;

    @Autowired
    @RoleQualifier
    RoleRepository roleModelDao;

    @Autowired
    @CompanyQualifier
    CompanyRepository companyDao;

    Collection<IUserModel> updateUsers = new ArrayList<IUserModel>();

    public static class Groups{
        public static final String USER_DAO_GROUP="userDaoGroup";
    }

    @BeforeClass(groups = {Groups.USER_DAO_GROUP})
    protected void setUp() throws Exception {
        super.setUp();
        assertNotNull(userModelDao);
        assertNotNull(roleModelDao);

    }

    @Test(  groups = {Groups.USER_DAO_GROUP},
            dataProviderClass = UserModelDataProvider.class,
            dataProvider = UserModelDataProvider.DataProviders.USER_DATA_PROVIDER
    )
    public void testSave(IUserModel userModel) {
        initDependents(userModel);
        userModelDao.saveOrUpdate((UserModel) userModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        IUserModel pesrsistUser = userModelDao.getModel(userModel.getObjectId());
        assertModel(userModel, pesrsistUser);
        IUserModel userByName = userModelDao.getUserByName(userModel.getLogin());
        assertEquals(userModel, userByName);
    }

    @Test(  groups = {Groups.USER_DAO_GROUP},
            dataProviderClass = UserModelDataProvider.class,
            dataProvider = UserModelDataProvider.DataProviders.USER_DATA_PROVIDER
    )
    public void updateTest(IUserModel userModel){
        initDependents(userModel);
        userModelDao.saveOrUpdate((UserModel) userModel);
        userModel = userModelDao.getModel(userModel.getObjectId());
        userModel.setPatronymic(UUID.randomUUID().toString());
        userModel.setLastName(UUID.randomUUID().toString());
        userModel.setFirstName(UUID.randomUUID().toString());
        userModel.setPhone(UUID.randomUUID().toString());
        userModel.setGender(IPerson.Gender.MAN);
        userModel.setMail(UUID.randomUUID().toString());
        userModelDao.saveOrUpdate((UserModel) userModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        IUserModel pesrsistUser = userModelDao.getModel(userModel.getObjectId());
        assertModel(userModel, pesrsistUser);
        IUserModel userByName = userModelDao.getUserByName(userModel.getLogin());
        assertEquals(userModel, userByName);

    }

    @Test(  groups = {Groups.USER_DAO_GROUP},
            dataProviderClass = UserModelDataProvider.class,
            dataProvider = UserModelDataProvider.DataProviders.USER_DATA_PROVIDER
    )
    public void deleteTest(IUserModel userModel){
        initDependents(userModel);
        userModelDao.saveOrUpdate((UserModel) userModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        Collection<IRoleModel> roleModels = userModel.getRoleModels();
        userModelDao.delete((UserModel) userModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        IUserModel persistUser = userModelDao.getModel(userModel.getObjectId());
        assertTrue(persistUser.isDeleted());
        IUserModel roleByName = userModelDao.getUserByName(userModel.getLogin());
        assertNull(roleByName);
        for(IRoleModel roleModel : roleModels){
            assertNotNull(roleModelDao.getModel(roleModel.getObjectId()));
        }
    }

    @Test(  groups = {Groups.USER_DAO_GROUP},
            dataProviderClass = UserModelDataProvider.class,
            expectedExceptions = ConstraintViolationException.class,
            dataProvider = UserModelDataProvider.DataProviders.USER_DATA_PROVIDER
    )
    public void doubleUserTest(IUserModel userModel){
        initDependents(userModel);
        userModelDao.saveOrUpdate((UserModel) userModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        IUserModel doubleUserModel = new UserModel();
        doubleUserModel.setLogin(userModel.getLogin());
        doubleUserModel.setPassword(userModel.getPassword());
        doubleUserModel.setPhone(userModel.getPhone());
        doubleUserModel.setFirstName(userModel.getFirstName());
        doubleUserModel.setLastName(userModel.getLastName());
        doubleUserModel.setGender(userModel.getGender());
        ((GenericJPARepository)getDao()).saveOrUpdate(doubleUserModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();
    }

    public void assertModel(IUserModel userModel, IUserModel pesrsistUser) {
        super.assertModel(userModel, pesrsistUser);
        assertEquals(pesrsistUser.getFirstName(), userModel.getFirstName());
        assertEquals(pesrsistUser.getPatronymic(),userModel.getPatronymic());
        assertEquals(pesrsistUser.getLastName(),userModel.getLastName());
        assertEquals(pesrsistUser.getLogin(),userModel.getLogin());
        assertEquals(pesrsistUser.getPhone(),userModel.getPhone());
        assertEquals(pesrsistUser.getMail(),userModel.getMail());
        for (IRoleModel roleModel : userModel.getRoleModels()){
            assertTrue(pesrsistUser.getRoleModels().contains(roleModel));
        }
       assertEquals(userModel.getPassword(), pesrsistUser.getPassword());
    }

    protected void initDependents(IUserModel userModel){
        companyDao.saveOrUpdate(userModel.getCompany());
        for (IRoleModel roleModel : userModel.getRoleModels()) {
            roleModelDao.saveOrUpdate((RoleModel) roleModel);
        }
    }

    @AfterClass
    public void tearDawn(){
        updateUsers.clear();
    }

    @Override
    protected UserRepository getDao() {
        return userModelDao;
    }


}
