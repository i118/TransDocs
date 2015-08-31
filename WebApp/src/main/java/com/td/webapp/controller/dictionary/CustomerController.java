package com.td.webapp.controller.dictionary;

import com.td.model.dto.dictionary.contractor.CustomerDTO;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.ICustomerModel;
import com.td.service.context.qualifier.ContractorCrud;
import com.td.service.crud.dictionary.contractor.ContractorCRUDService;
import com.td.service.crud.dictionary.contractor.CustomerCRUDService;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by konstantinchipunov on 27.08.14.
 */
@Controller
@RequestMapping("/"+ CustomerController.CONTROLLER_NAME)
public class CustomerController extends ContractorController<CustomerModel> {

    public static final String CONTROLLER_NAME = "Customer";

    ContractorCRUDService<CustomerModel> customerService;

    @RequestMapping(value = "/"+ AbstractController.RequestName.UPDATE_OBJECT +"/{persistentId}", method = RequestMethod.PUT,
            headers = CONTENT_TYPE)
    public @ResponseBody
    IResponse updateObject(@RequestBody CustomerDTO persistent,@RequestParam Map<String, String> args){
        IResponse response = new ResponseImpl();
        ((CustomerCRUDService) getContractorService()).updateDictionaryObject(persistent, args);
        response.setSuccess(true);
        return response;
    }

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
