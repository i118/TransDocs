package com.td.service.command.crud.contractor;

import com.td.model.entity.dictionary.company.CarrierPersonModel;
import com.td.model.entity.dictionary.company.ContractPerson;
import com.td.model.entity.dictionary.company.ContractPersonModel;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindContractPerson;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zerotul.
 */
@Component
@FindContractPerson
public class FindContractPersonCommand extends AbstractProducerCommand<UUID, List<ContractPerson>> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<UUID, List<ContractPerson>> executeInternal(ProducerCommandContext<UUID, List<ContractPerson>> context, Map<String, Argument> args) throws Exception {
        CRUDService<Contractor> crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        List<ContractPerson> contractPersons = crudService.getReference(context.getTarget()).getPersons();
        contractPersons.size();
        context.setProduced(Collections.unmodifiableList(contractPersons));
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
