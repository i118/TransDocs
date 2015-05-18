package com.td.model.repository.dictionary.contractor;

import org.testng.annotations.DataProvider;

/**
 * Created by konstantinchipunov on 21.08.14.
 */
public class CustomerModelDataProvider {

    public static class DataProviders{
        public static final String CUSTOMER_DATA = "customerData";
    }

    @DataProvider
    public static Object[][] customerData(){
        return CustomerDataService.getInstance().getDataArray();
    }
}
