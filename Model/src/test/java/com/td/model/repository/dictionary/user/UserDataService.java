package com.td.model.repository.dictionary.user;

import com.td.model.repository.dictionary.IDataService;
import com.td.model.entity.dictionary.IPerson;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;

/**
 * Created by konstantinchipunov on 28.07.14.
 */
public class UserDataService implements IDataService<IUserModel> {


    private static volatile UserDataService userDataService;

    private UserDataService() {
    }

    public static UserDataService getInstance() {
        if (userDataService == null) {
            synchronized (UserDataService.class) {
                if (userDataService == null) {
                    userDataService = new UserDataService();
                }
            }
        }
        return userDataService;
    }

    @Override
    public IUserModel[][] getDataArray() {
        IUserModel userModel = new UserModel();
        userModel.setLogin("test_user");
        userModel.setFirstName("test_user");
        userModel.setLastName("test_user");
        userModel.setPatronymic("test_user");
        userModel.setMail("test@mail.ru");
        userModel.setPhone("111111");
        userModel.setGender(IPerson.Gender.MAN);
        userModel.setPassword(new Password("1", userModel));
        CompanyModel companyModel = new CompanyModel();
        companyModel.setFullName("djsijlsdjl");
        companyModel.setLegalForm(JuridicalPerson.LegalForm.CJSC);;
        companyModel.setShortName("nniiiodsop");
        companyModel.setLogin("nniiiodsop");
        userModel.setCompany(companyModel);
        return new IUserModel[][]{{userModel}};
    }
}
