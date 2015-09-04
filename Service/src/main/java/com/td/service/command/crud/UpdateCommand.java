package com.td.service.command.crud;

import com.td.model.dto.ModelDTO;
import com.td.model.entity.Persistent;
import com.td.service.command.*;
import com.td.service.command.CommandArguments.ApplyChanges;
import com.td.service.command.argument.Argument;
import com.td.service.command.qualifier.ApplyChangesQualifier;
import com.td.service.command.crud.qualifier.UpdateCommandQualifier;
import com.td.service.context.qualifier.CommandServiceQualifier;
import com.td.service.crud.CRUDService;
import org.springframework.stereotype.Component;


import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@UpdateCommandQualifier
public class UpdateCommand<T extends ModelDTO, D extends Persistent> extends AbstractProducerCommand<T, D> {

    private final Command<ModelDTO> applyChanges;

    private final CommandService commandService;

    public static class Arguments{
        public static final String CRUD_SERVICE = "crudService";
    }

    @Inject
    public UpdateCommand(@ApplyChangesQualifier Command<ModelDTO> applyChanges, @CommandServiceQualifier CommandService commandService) {
        this.applyChanges = applyChanges;
        this.commandService = commandService;
    }

    @Override
    protected ProducerCommandContext<T, D> executeInternal(ProducerCommandContext<T, D> context, Map<String, Argument> args) throws Exception {
        CRUDService<D> crudService = getArgumentValue(Arguments.CRUD_SERVICE, args);
        T dto = context.getTarget();
        D persistent = crudService.getReference(dto.getObjectId());
        CommandContext<ModelDTO> applyContext = commandService.execute(dto, applyChanges, ApplyChanges.DESTINATION.valueOf(persistent));
        if(applyContext.isFailure()) throw applyContext.getException();
        crudService.update(persistent);
        context.setProduced(persistent);
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.CRUD_SERVICE);
    }
}
