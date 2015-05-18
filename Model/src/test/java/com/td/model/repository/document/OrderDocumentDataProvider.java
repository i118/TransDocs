package com.td.model.repository.document;

import com.td.model.entity.document.OrderDocumentModel;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.testng.annotations.DataProvider;

import java.math.BigDecimal;

/**
 * Created by zerotul on 26.01.15.
 */
public class OrderDocumentDataProvider {

    public static class DataProviders{
        public static final String ORDER_DOCUMENT_DATA = "orderDocumentData";
    }

    @DataProvider
    public static Object[][] orderDocumentData(){
        OrderDocumentModel model = new OrderDocumentModel();
        model.setAmountTransaction(Money.of(CurrencyUnit.of("RUB"), new BigDecimal(3)));
        return new Object[][]{
                {model}
        };
    }
}
