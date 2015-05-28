package com.td.webapp.controller.dictionary;

import com.td.model.annotation.SchemaAware;
import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.crud.dictionary.DictionaryCRUDService;
import com.td.service.crud.dictionary.company.CompanyService;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 25.03.15.
 */
@Controller
@RequestMapping("/"+CompanyController.CONTROLLER_NAME)
public class CompanyController extends AbstractDictionaryController<CompanyModel> {

    public static final String CONTROLLER_NAME = "Company";

    private CompanyService companyService;

    public static class RequestName extends AbstractDictionaryController.RequestName {
        public static final String CURRENT_COMPANY = "current";
    }

    @RequestMapping(value = "/" + RequestName.GET_OBJECT + "/" + RequestName.CURRENT_COMPANY, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse<CompanyModel> getCurrentCompany() {

        IResponse<CompanyModel> response = new ResponseImpl<>();
        response.addResult(companyService.getCurrentCompany());
        response.setSuccess(true);
        return response;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    @Override
    public CompanyService getDictionaryService() {
        return companyService;
    }

    @Override
    @SchemaAware
    public void deleteDictionary(CompanyModel persistent, Map<String, String> arguments) {
        companyService.deleteDictionaryObject(persistent, arguments);
    }

    @Override
    @SchemaAware
    public void createDictionary(CompanyModel persistent, Map<String, String> arguments) {
       companyService.createDictionaryObject(persistent, arguments);
    }

    @Override
    @SchemaAware
    public void updateDictionary(CompanyModel persistent, Map<String, String> arguments) {
       companyService.updateDictionaryObject(persistent, arguments);
    }

    @Override
    public CompanyModel getDictionary(UUID persistentId, Map<String, String> arguments) {
        return companyService.getModel(persistentId);
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
