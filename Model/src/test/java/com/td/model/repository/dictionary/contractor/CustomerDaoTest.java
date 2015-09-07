package com.td.model.repository.dictionary.contractor;

import com.td.model.context.qualifier.ContractorQualifier;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.company.ICustomerModel;
import com.td.model.entity.dictionary.dataset.DictionaryDataSetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.zerotul.specification.Specifications.from;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
public class CustomerDaoTest extends AbstractDaoTest<ICustomerModel> {

    @Autowired
    @ContractorQualifier(ContractorQualifier.Type.CUSTOMER)
    private ContractorJPARepository<CustomerModel> customerModelDao;


    @BeforeClass
    protected void setUp() throws Exception{
        super.setUp();
        assertNotNull(getDao());
    }

    @Test(dataProviderClass = CustomerModelDataProvider.class,
            dataProvider = CustomerModelDataProvider.DataProviders.CUSTOMER_DATA)
    public void testSaveCustomer(CustomerModel customerModel){
      customerModel = getDao().saveOrUpdate(customerModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();
        ((DictionaryRepository)getDao()).findDataSet(from(DictionaryDataSetImpl.class).endFrom());
       ICustomerModel persistCustomer = customerModelDao.findById(customerModel.getObjectId());
       assertModel(customerModel, persistCustomer);
    }


    @Override
    public void assertModel(ICustomerModel persistent, ICustomerModel persistent2) {
        super.assertModel(persistent, persistent2);
        assertEquals(persistent.getFullName(), persistent2.getFullName());
        assertEquals(persistent.getShortName(), persistent2.getShortName());
        assertEquals(persistent.getLegalAddress(), persistent2.getLegalAddress());
        assertEquals(persistent.getLegalForm(), persistent2.getLegalForm());
        assertEquals(persistent.getDescription(), persistent2.getDescription());
        assertEquals(persistent.getComment(), persistent2.getComment());
        assertEquals(persistent.getAccountDetails(), persistent2.getAccountDetails());
        assertEquals(persistent.getPersons(), persistent2.getPersons());
        assertEquals(persistent.getPhone(), persistent2.getPhone());
        assertEquals(persistent.getEmail(), persistent2.getEmail());
    }

    @Override
    protected ContractorJPARepository<CustomerModel> getDao() {
        return customerModelDao;
    }
}
