package com.td.service.command.crud;

import com.td.model.entity.Persistent;
import com.td.service.command.AbstractCommand;
import com.td.service.command.CommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.DeleteCommandQualifier;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@DeleteCommandQualifier
public class DeleteCommand<T extends Persistent> extends AbstractCommand<T> {

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Override
    protected CommandContext<T> executeInternal(CommandContext<T> context, Map<String, Argument> args) throws Exception {
        CRUDService crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        crudService.delete(context.getTarget());
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
