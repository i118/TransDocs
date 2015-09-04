package com.td.webapp.controller.dictionary;

import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.security.SecurityService;
import com.td.service.command.ProducerCommand;
import com.td.service.command.crud.qualifier.FindUserBy;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.context.qualifier.UserCRUDFacade;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.CRUDFacade;
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
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 28.02.14.
 */

@Controller
@RequestMapping("/" + UserController.CONTROLLER_NAME)
public class UserController extends AbstractDictionaryController<UserModel, UserDTO> {

    public static final String CONTROLLER_NAME = "User";

    private SecurityService securityService;

    private ProducerCommand<UUID, List<UserModel>> findByCompany;

    private CRUDFacade<UserModel, UserDTO> facade;


    public static class RequestName extends AbstractDictionaryController.RequestName {
        public static final String CURRENT_USER = "current";

        public static final String FIND_BY_COMPANY = "find.byCompany";
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }


    @RequestMapping(value = "/" + RequestName.GET_OBJECT + "/" + RequestName.CURRENT_USER, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse<IUserModel> getCurrentUser() {
        IResponse<IUserModel> response = new ResponseImpl<>();
        response.addResult(securityService.getCurrentUser());
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/" + RequestName.FIND_BY_COMPANY, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody IResponse<UserModel> findByCompany(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String companyId = jsonFilter.findByProperty("companyId");
        if (companyId == null) throw new IllegalArgumentException("company filter not found");
        IResponse<UserModel> response = new ResponseImpl<>();
        response.addResults(getFacade().read(UUID.fromString(companyId), findByCompany));
        response.setSuccess(true);
        return response;
    }


    @Override
    public void deleteDictionary(UUID persistentId, Map<String, String> arguments) {
        getFacade().delete(persistentId, obtainArguments(arguments));
    }

    @Override
    public void createDictionary(UserModel persistent, Map<String, String> arguments) {
        getFacade().create(persistent, obtainArguments(arguments));
    }

    @Override
    public UserModel updateDictionary(UserDTO dto, Map<String, String> arguments) {
      return  getFacade().update(dto, obtainArguments(arguments));
    }

    public UserModel getDictionary(UUID persistentId, Map<String, String> arguments) {
        return getFacade().findById(persistentId);
    }


    public ProducerCommand<UUID, List<UserModel>> getFindByCompany() {
        return findByCompany;
    }

    @Inject
    @FindUserBy(findBy = FindUserBy.FindBy.COMPANY_ID)
    public void setFindByCompany(ProducerCommand<UUID, List<UserModel>> findByCompany) {
        this.findByCompany = findByCompany;
    }

    @Override
    public CRUDFacade<UserModel, UserDTO> getFacade() {
        return facade;
    }

    @Inject
    @UserCRUDFacade
    public void setFacade(CRUDFacade<UserModel, UserDTO> facade) {
        this.facade = facade;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    @Inject
    @SecurityQualifier
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
