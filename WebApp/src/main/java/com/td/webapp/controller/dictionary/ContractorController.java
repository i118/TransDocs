package com.td.webapp.controller.dictionary;

import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.IContractPerson;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.service.crud.dictionary.contractor.ContractorCRUDService;
import com.td.webapp.mapper.Filter;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
public abstract class ContractorController<T extends JuridicalPerson & Contractor> extends AbstractDictionaryController<T> {

    public static class RequestName extends AbstractDictionaryController.RequestName{
        public static final String GET_PERSONS = "get.persons";
    }

    @Override
    public void deleteDictionary(T persistent, Map<String, String> arguments) {

    }

    @Override
    public void createDictionary(T persistent, Map<String, String> arguments) {
        getContractorService().createDictionaryObject(persistent, arguments);
    }

    @Override
    public void updateDictionary(T persistent, Map<String, String> arguments) {
        getContractorService().createDictionaryObject(persistent, arguments);
    }

    @Override
    public T getDictionary(UUID  persistentId, Map<String, String> arguments){
        return getContractorService().getModel(persistentId);
    }

    @RequestMapping(value = "/" + RequestName.GET_PERSONS)
    protected @ResponseBody
    IResponse getPersons(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String contractorId = jsonFilter.findByProperty("contractorId");
        if(contractorId ==null) throw new IllegalArgumentException("company filter not found");
        IResponse response = new ResponseImpl();
        getContractorService().getReference(UUID.fromString(contractorId), (T customer) -> {
            customer.getPersons().stream().filter((IContractPerson person) -> !person.isDeleted()).forEach((IContractPerson person) -> {
                person.getEmail();
                response.addResult(person);
            });
        });
        response.setSuccess(true);

        return response;
    }

    public abstract ContractorCRUDService<T, ContractorRepository<T>> getContractorService();

    @Override
    public ContractorCRUDService<T, ContractorRepository<T>> getDictionaryService() {
        return getContractorService();
    }

    public abstract void setContractorService(ContractorCRUDService<T, ContractorRepository<T>> customerService);
}
