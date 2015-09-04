package com.td.service.command.crud;

import com.td.model.entity.Persistent;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.CreateCommandQualifier;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@CreateCommandQualifier
public class CreateCommand<T extends Persistent> extends AbstractProducerCommand<T, T> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected ProducerCommandContext<T, T> executeInternal(ProducerCommandContext<T,T > context, Map<String, Argument> args) throws Exception {
        CRUDService<T> crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        T persistent = crudService.saveOrUpdate(context.getTarget());
        context.setProduced(persistent);
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
