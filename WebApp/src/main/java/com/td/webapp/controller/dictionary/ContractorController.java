package com.td.webapp.controller.dictionary;

import com.td.model.dto.dictionary.contractor.JuridicalPersonDTO;
import com.td.model.entity.dictionary.company.*;
import com.td.service.command.ProducerCommand;
import com.td.service.command.crud.qualifier.FindContractPerson;
import com.td.webapp.mapper.Filter;
import com.td.webapp.response.IResponse;
import com.td.webapp.response.ResponseImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by konstantinchipunov on 12.11.14.
 */
public abstract class ContractorController<T extends JuridicalPerson & Contractor, D extends JuridicalPersonDTO> extends AbstractDictionaryController<T, D> {

    private ProducerCommand<UUID, List<ContractPerson<Contractor>>> findPerson;

    public static class RequestName extends AbstractDictionaryController.RequestName{
        public static final String GET_PERSONS = "get.persons";
    }

    @Override
    public void deleteDictionary(UUID persistentId, Map<String, String> arguments) {
        getFacade().delete(persistentId, obtainArguments(arguments));
    }

    @Override
    public void createDictionary(T persistent, Map<String, String> arguments) {
        getFacade().create(persistent);
    }

    @Override
    public T updateDictionary(D dto, Map<String, String> arguments) {
      return getFacade().update(dto);
    }

    @Override
    public T getDictionary(UUID  persistentId, Map<String, String> arguments){
        return getFacade().findById(persistentId);
    }

    @RequestMapping(value = "/" + RequestName.GET_PERSONS)
    protected @ResponseBody
    IResponse getPersons(@RequestParam String filter) {
        Filter jsonFilter = new Filter(filter);
        String contractorId = jsonFilter.findByProperty("contractorId");
        if(contractorId ==null) throw new IllegalArgumentException("company filter not found");
        IResponse response = new ResponseImpl();
        response.addResults(getFacade().read(UUID.fromString(contractorId), findPerson));
        response.setSuccess(true);

        return response;
    }

    public ProducerCommand<UUID, List<ContractPerson<Contractor>>> getFindPerson() {
        return findPerson;
    }

    @Inject
    @FindContractPerson
    public void setFindPerson(ProducerCommand<UUID, List<ContractPerson<Contractor>>> findPerson) {
        this.findPerson = findPerson;
    }
}
