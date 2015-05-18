package com.td.model.repository.dictionary.user;

import org.testng.annotations.DataProvider;

/**
 * Created by konstantinchipunov on 03.07.14.
 */
public class UserModelDataProvider {

    public static class DataProviders{
        public static final String USER_DATA_PROVIDER = "userModelData";
    }

    @DataProvider
    public static Object[][] userModelData() {
        return UserDataService.getInstance().getDataArray();
    }
}
