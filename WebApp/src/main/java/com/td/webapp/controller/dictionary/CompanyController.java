package com.td.webapp.controller.dictionary;

import com.td.model.annotation.SchemaAware;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.contractor.CompanyDTO;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.crud.qualifier.FindCompanyBy;
import com.td.service.context.qualifier.CompanyCRUDFacade;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.crud.CRUDFacade;
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
public class CompanyController extends AbstractDictionaryController<CompanyModel, CompanyDTO> {

    public static final String CONTROLLER_NAME = "Company";

    private AbstractProducerCommand<Object, CompanyModel> findCurrent;

    public static class RequestName extends AbstractDictionaryController.RequestName {
        public static final String CURRENT_COMPANY = "current";
    }

    private CRUDFacade<CompanyModel, CompanyDTO> facade;

    @RequestMapping(value = "/" + RequestName.GET_OBJECT + "/" + RequestName.CURRENT_COMPANY, method = {RequestMethod.GET, RequestMethod.POST},
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse<CompanyModel> getCurrentCompany() {

        IResponse<CompanyModel> response = new ResponseImpl<>();
        response.addResult(getFacade().read(null, findCurrent));
        response.setSuccess(true);
        return response;
    }

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    @Override
    @SchemaAware
    public void deleteDictionary(UUID persistentId, Map<String, String> arguments) {
        getFacade().delete(persistentId, obtainArguments(arguments));
    }

    @Override
    @SchemaAware
    public void createDictionary(CompanyModel persistent, Map<String, String> arguments) {
       getFacade().create(persistent);
    }

    @Override
    @SchemaAware
    public CompanyModel updateDictionary(CompanyDTO dto, Map arguments) {
        return getFacade().update(dto, obtainArguments(arguments));
    }


    @Override
    public CompanyModel getDictionary(UUID persistentId, Map<String, String> arguments) {
        return getFacade().findById(persistentId);
    }

    public AbstractProducerCommand<Object, CompanyModel> getFindCurrent() {
        return findCurrent;
    }

    @Inject
    @FindCompanyBy(findBy = FindCompanyBy.FindBy.CURRENT)
    public void setFindCurrent(AbstractProducerCommand<Object, CompanyModel> findCurrent) {
        this.findCurrent = findCurrent;
    }

    @Override
    public CRUDFacade<CompanyModel, CompanyDTO> getFacade() {
        return facade;
    }

    @Inject
    @CompanyCRUDFacade
    public void setFacade(CRUDFacade<CompanyModel, CompanyDTO> facade) {
        this.facade = facade;
    }
}
