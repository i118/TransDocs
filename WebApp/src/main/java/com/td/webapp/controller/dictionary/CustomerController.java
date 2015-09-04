package com.td.webapp.controller.dictionary;

import com.td.model.dto.dictionary.contractor.CustomerDTO;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.service.context.qualifier.ContractorCRUDFacade;
import com.td.service.crud.CRUDFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by konstantinchipunov on 27.08.14.
 */
@Controller
@RequestMapping("/"+ CustomerController.CONTROLLER_NAME)
public class CustomerController extends ContractorController<CustomerModel, CustomerDTO> {

    public static final String CONTROLLER_NAME = "Customer";

    private CRUDFacade<CustomerModel, CustomerDTO> facade;

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    @Inject
    @ContractorCRUDFacade(ContractorCRUDFacade.Type.CUSTOMER)
    public void setFacade(CRUDFacade<CustomerModel, CustomerDTO> crudFacade) {
        this.facade = crudFacade;
    }

    @Override
    public CRUDFacade<CustomerModel, CustomerDTO> getFacade() {
        return facade;
    }
}
