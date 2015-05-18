package com.td.service.crud.dictionary;

import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 30.07.14.
 */
public class UserDataProvider {
    public static class DataProviders{
        public static final String CREATE_USER_DATA = "createUserData";
        public static final String USER_DATA = "userData";
        public static final String USER_DATA_LIST = "userDataList";
    }

    @DataProvider
    public static Object[][] createUserData(){
        IUserModel user1 = new UserModel();
        fillUser(user1);
        IUserModel user2 = new UserModel();
        fillUser(user2);
        IUserModel user3 = new UserModel();
        fillUser(user3);
        return new Object[][]{
                {user1, "1"},
                {user2, "2"},
                {user3, "3"}
        };
    }

    @DataProvider
    public static Object[][] userData(){
        IUserModel user1 = new UserModel();
        fillUser(user1);
        IUserModel user2 = new UserModel();
        fillUser(user2);
        IUserModel user3 = new UserModel();
        fillUser(user3);
        return new Object[][]{
                {user1},
                {user2},
                {user3}
        };
    }

    @DataProvider
    public static Object[][] userDataList(){
        IUserModel user1 = new UserModel();
        fillUser(user1);
        IUserModel user2 = new UserModel();
        fillUser(user2);
        IUserModel user3 = new UserModel();
        fillUser(user3);
        List<IUserModel> userModels = new ArrayList<>();
        userModels.add(user1);
        userModels.add(user2);
        userModels.add(user3);
        return new Object[][]{
                {userModels}
        };
    }

    public static void fillUser(IUserModel userModel){
        userModel.setLogin(UUID.randomUUID().toString());
        userModel.setFirstName(UUID.randomUUID().toString());
        userModel.setLastName(UUID.randomUUID().toString());
        userModel.setPatronymic(UUID.randomUUID().toString());
        userModel.setMail(UUID.randomUUID().toString());
        userModel.setPhone(UUID.randomUUID().toString());
        userModel.setGender(IPerson.Gender.MAN);
        userModel.setCompany(new CompanyModel());
        Set<IRoleModel> roleModels = new HashSet<>();
        roleModels.add(new RoleModel());
    }
}
