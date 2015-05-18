package com.td.model.repository.dictionary.contractor;

import com.td.model.context.qualifier.ContractorQualifier;
import com.td.model.repository.GenericJPARepository;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.entity.dictionary.company.CarrierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
public class CarrierDaoTest extends AbstractDaoTest<CarrierModel> {

    @Autowired
    @ContractorQualifier(ContractorQualifier.Type.CARRIER)
    private ContractorJPARepository<CarrierModel> contractorModelDao;

    @BeforeClass
    protected void setUp() throws Exception{
        super.setUp();
        assertNotNull(getDao());
    }

    @Test(dataProviderClass = CarrierDataProvider.class,
            dataProvider = CarrierDataProvider.DataProviders.CARRIER_DATA)
    public void testSaveCarrier(CarrierModel customerModel){
        getDao().saveOrUpdate(customerModel);
        ((GenericJPARepository)getDao()).getEntityManager().flush();

        CarrierModel persistCustomer = contractorModelDao.getModel(customerModel.getObjectId());
        assertModel(customerModel, persistCustomer);
    }


    @Override
    public void assertModel(CarrierModel persistent, CarrierModel persistent2) {
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
    protected ContractorJPARepository<CarrierModel> getDao() {
        return contractorModelDao;
    }

}
