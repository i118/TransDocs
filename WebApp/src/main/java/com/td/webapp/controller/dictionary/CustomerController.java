package com.td.webapp.controller.dictionary;

import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.ICustomerModel;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.crud.dictionary.contractor.ContractorCRUDService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by konstantinchipunov on 27.08.14.
 */
@Controller
@RequestMapping("/"+ CustomerController.CONTROLLER_NAME)
public class CustomerController extends ContractorController<CustomerModel> {

    public static final String CONTROLLER_NAME = "Customer";

    ContractorCRUDService<CustomerModel> customerService;

    @Override
    public String getControllerName() {
        return CONTROLLER_NAME;
    }

    public ContractorCRUDService<CustomerModel> getContractorService() {
        return customerService;
    }

    @Inject
    @ContractorCrud(ContractorCrud.Type.CUSTOMER)
    public void setContractorService(ContractorCRUDService<CustomerModel> customerService) {
        this.customerService = customerService;
    }
}
