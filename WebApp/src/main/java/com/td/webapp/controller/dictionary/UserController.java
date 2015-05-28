package com.td.webapp.controller.dictionary;

import com.td.model.context.qualifier.UserQualifier;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.dictionary.company.CompanyService;
import com.td.service.crud.dictionary.user.UserCRUDService;
import com.td.webapp.mapper.Filter;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 28.02.14.
 */

@Controller
@RequestMapping("/" + UserController.CONTROLLER_NAME)
public class UserController extends AbstractDictionaryController<UserModel> {

    public static final String CONTROLLER_NAME = "User";

    private UserCRUDService userService;

    private CompanyService companyService;

    public static class RequestName extends AbstractDictionaryController.RequestName {
        public static final String CURRENT_USER = "current";

        public static final String FIND_BY_COMPANY = "find.byCompany";
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }


    public UserCRUDService getDictionaryService() {
        return userService;
    }

    @RequestMapping(value = "/" + RequestName.GET_OBJECT + "/" + RequestName.CURRENT_USER, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse<IUserModel> getCurrentUser() {
        IResponse<IUserModel> response = new ResponseImpl<>();
        response.addResult(userService.getCurrentUser());
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.FIND_BY_COMPANY, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse<IUserModel> findByCompany(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String companyId = jsonFilter.findByProperty("companyId");
        if(companyId ==null) throw new IllegalArgumentException("company filter not found");
        IResponse<IUserModel> response = new ResponseImpl<>();
        getCompanyService().getReference(UUID.fromString(companyId), (CompanyModel company)->{
           company.getUsers().forEach((IUserModel user)->{
               response.addResult(user);
           });
        });
        response.setSuccess(true);
        return response;
    }

    @Inject
    @UserCrud
    public void setUserService(UserCRUDService userService) {
        this.userService = userService;
    }


    @Override
    public void deleteDictionary(UserModel persistent, Map<String, String> arguments) {
        getDictionaryService().deleteDictionaryObject(persistent, arguments);
    }

    @Override
    public void createDictionary(UserModel persistent, Map<String, String> arguments) {
        getDictionaryService().createDictionaryObject(persistent, arguments);
    }

    @Override
    public void updateDictionary(UserModel persistent, Map<String, String> arguments) {
        getDictionaryService().updateDictionaryObject(persistent, arguments);
    }

    public UserModel getDictionary(UUID persistentId, Map<String, String> arguments) {
        return getDictionaryService().getModel(persistentId, (UserModel userModel) -> {
            userModel.getRoleModels().parallelStream().forEach((IRoleModel roleModel) -> {
                roleModel.getRoleName();
            });
        });
    }

    public CompanyService getCompanyService() {
        return companyService;
    }

    @Inject
    @CompanyCrud
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }
}
