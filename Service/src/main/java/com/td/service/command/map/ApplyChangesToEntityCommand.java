package com.td.service.command.map;

import com.td.model.context.qualifier.MapperQualifier;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.mapper.Mapper;
import com.td.model.entity.Model;
import com.td.service.command.AbstractCommand;
import com.td.service.command.CommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.qualifier.ApplyChangesQualifier;
import org.springframework.stereotype.Component;


import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@ApplyChangesQualifier
public class ApplyChangesToEntityCommand extends AbstractCommand<ModelDTO> {

    public static class Arguments{
        public static final String DESTINATION = "destination";
    }

    private final Mapper mapper;

    @Inject
    public ApplyChangesToEntityCommand(@MapperQualifier Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected CommandContext<ModelDTO> executeInternal(CommandContext<ModelDTO> context, Map<String, Argument> args) throws Exception {
        Model model = getArgumentValue(Arguments.DESTINATION, args);
        ModelDTO dto = context.getTarget();
        mapper.map(dto, model);
        return context;
    }

    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.DESTINATION);
    }
}
