package com.td.service.command.crud.contractor;

import com.td.model.entity.dictionary.company.CarModel;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindCars;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by zerotul.
 */
@Component
@FindCars
public class FindCarsCommand extends AbstractProducerCommand<UUID, List<CarModel>> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<UUID, List<CarModel>> executeInternal(ProducerCommandContext<UUID, List<CarModel>> context, Map<String, Argument> args) throws Exception {
        CRUDService<CarrierModel> crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        List<CarModel> cars = crudService.getReference(context.getTarget()).getCars();
        cars.size();
        context.setProduced(Collections.unmodifiableList(cars));
        return context;
    }


    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
