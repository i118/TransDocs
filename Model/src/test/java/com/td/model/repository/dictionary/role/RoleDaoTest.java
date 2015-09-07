package com.td.model.repository.dictionary.role;

import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.zerotul.specification.Specifications.from;

/**
 * Created by konstantinchipunov on 04.07.14.
 */
public class RoleDaoTest extends AbstractDaoTest<IRoleModel>{

    @Autowired
    @RoleQualifier
    RoleRepository roleModelDao;

    @BeforeClass
    public void setUp() throws Exception {
        super.setUp();
        assertNotNull(roleModelDao);
    }

    public static class DataProviders{
        public static final String ROLE_DATA_PROVIDER = "roleModelData";
        public static final String ROLE_DATA_PROVIDER_COLLECTION = "roleModelDataCollection";
    }

    public static class Groups{
        public static final String ROLE_DAO_GROUP="roleDaoGroup";
    }

    @BeforeTest(groups = Groups.ROLE_DAO_GROUP)
    public void beforeClass() {
    }


    @Test(  groups = {Groups.ROLE_DAO_GROUP},
            dataProviderClass = RoleModelDataProvider.class,
            dataProvider = DataProviders.ROLE_DATA_PROVIDER
    )
    public void testRoleDao(IRoleModel roleModel){
        roleModel = roleModelDao.saveOrUpdate((RoleModel) roleModel);
        IRoleModel roleById = roleModelDao.findById(roleModel.getObjectId());
        IRoleModel roleByName = roleModelDao.getRoleByName(roleModel.getRoleName());
        assertModel(roleModel, roleById);
        assertEquals(roleById, roleByName);
    }


    @Test(  groups = {Groups.ROLE_DAO_GROUP},
            expectedExceptions = UnsupportedOperationException.class,
            dataProviderClass = RoleModelDataProvider.class,
            dataProvider = DataProviders.ROLE_DATA_PROVIDER
    )
    public void testDeleteRoleModel(IRoleModel roleModel){
       roleModelDao.delete((RoleModel) roleModel);
    }

    @Test(
            enabled = false,
            groups = {Groups.ROLE_DAO_GROUP},
            dataProviderClass = RoleModelDataProvider.class,
            dataProvider = DataProviders.ROLE_DATA_PROVIDER_COLLECTION
    )
    public void testGetAll(Collection<IRoleModel> roles){
        for(IRoleModel role : roles){
            roleModelDao.saveOrUpdate((RoleModel) role);
        }
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((GenericJPARepository)getDao()).getEntityManager().clear();

        assertEquals(RoleDataService.getInstance().getDataCollection().size(), roleModelDao.findDataSet(from(DictionaryDataSetImpl.class).endFrom()).size());
    }

    @Override
    public void assertModel(IRoleModel roleModel, IRoleModel persistRoleModel) {
        super.assertModel(roleModel, persistRoleModel);
        assertEquals(roleModel.getRoleName(), persistRoleModel.getRoleName());
        assertEquals(roleModel.getDescription(), persistRoleModel.getDescription());
    }


    @Override
    protected RoleRepository getDao() {
        return roleModelDao;
    }
}
