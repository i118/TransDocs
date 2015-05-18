package com.td.model.repository.dictionary.role;

import org.testng.annotations.DataProvider;

/**
 * Created by konstantinchipunov on 04.07.14.
 */
public class RoleModelDataProvider {

    @DataProvider
    public static Object[][] roleModelData(){
       return RoleDataService.getInstance().getDataArray();
    }

    @DataProvider
    public static Object[][] roleModelDataCollection(){
        return new Object[][]{
                {RoleDataService.getInstance().getDataCollection()}
        };
    }
}
