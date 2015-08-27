package com.td.model.repository;

import com.td.model.repository.context.ModelTestContext;
import com.td.model.entity.Persistent;
import com.td.model.multitenant.SchemaProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by konstantinchipunov on 03.07.14.
 */
@ContextConfiguration(classes = {ModelTestContext.class})
@TransactionConfiguration( transactionManager = "transactionManager")
public abstract class AbstractDaoTest<T extends Persistent> extends AbstractTransactionalTestNGSpringContextTests {

    protected abstract <D extends IRepository> D getDao();

    @Autowired
    ApplicationContext context;

    @BeforeClass
    protected void setUp() throws Exception{
        SchemaProviderImpl.setCurrentSchema("td_admin");
        assertNotNull(context);
    }



    public void assertModel(T persistent, T persistent2){
       assertEquals(persistent.getObjectId(), persistent2.getObjectId());
       assertEquals(persistent.getObjectType(), persistent2.getObjectType());
       assertEquals(persistent.getVersion(), persistent2.getVersion(), "persistent1 version=" + persistent.getVersion() + " persistent2 version=" + persistent2.getVersion());
       assertNotNull(persistent2.getCreationDate());
       assertNotNull(persistent2.getModifyDate());
    }

    public ApplicationContext getContext() {
        return context;
    }

}
