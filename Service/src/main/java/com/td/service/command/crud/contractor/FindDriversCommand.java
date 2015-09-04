package com.td.service.command.crud.contractor;

import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.DriverModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindDrivers;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zerotul.
 */
@Component
@FindDrivers
public class FindDriversCommand extends AbstractProducerCommand<UUID, List<DriverModel>> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<UUID, List<DriverModel>> executeInternal(ProducerCommandContext<UUID, List<DriverModel>> context, Map<String, Argument> args) throws Exception {
        CRUDService<CarrierModel> crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        List<DriverModel> drivers = crudService.getReference(context.getTarget()).getDrivers();
        drivers.size();
        context.setProduced(Collections.unmodifiableList(drivers));
        return context;
    }


    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
