package com.td.model.repository.document;

import com.td.model.context.qualifier.DocumentQualifier;
import com.td.model.repository.AbstractDaoTest;
import com.td.model.entity.document.OrderDocumentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Created by zerotul on 26.01.15.
 */
public class OrderDocumentDaoTest extends AbstractDaoTest<OrderDocumentModel> {

    @Autowired
    @DocumentQualifier(DocumentQualifier.Type.ORDER)
    private DocumentJPARepository<OrderDocumentModel> orderDocumentDao;


    @BeforeClass
    protected void setUp() throws Exception{
        super.setUp();
        assertNotNull(getDao());
    }

    @Test(dataProviderClass = OrderDocumentDataProvider.class, dataProvider = OrderDocumentDataProvider.DataProviders.ORDER_DOCUMENT_DATA)
    public void testSaveOrder(OrderDocumentModel orderDocumentModel){
        getDao().saveOrUpdate(orderDocumentModel);
        getDao().getEntityManager().flush();
        getDao().getEntityManager().clear();
        OrderDocumentModel persistOrderDocumentModel = getDao().findById(orderDocumentModel.getObjectId());

        assertModel(orderDocumentModel, persistOrderDocumentModel);
    }

    @Override
    public void assertModel(OrderDocumentModel persistent, OrderDocumentModel persistent2) {
        super.assertModel(persistent, persistent2);
        assertNotNull(persistent2.getIncomingNumber());
        assertNotNull(persistent2.getOutgoingNumber());
        assertEquals(persistent.getCarrier(), persistent2.getCarrier());
        assertEquals(persistent.getCustomer(), persistent2.getCustomer());
        assertEquals(persistent.getAmountTransaction(), persistent2.getAmountTransaction());
    }

    @Override
    protected DocumentJPARepository<OrderDocumentModel> getDao() {
        return orderDocumentDao;
    }
}
