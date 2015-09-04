package com.td.service.command;

import com.td.service.command.argument.Argument;
import com.td.service.context.qualifier.CommandServiceQualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zerotul.
 */
@Service
@CommandServiceQualifier
public class CommandServiceImpl implements CommandService {


    @Override
    public <T> CommandContext<T> execute(T target, Command<T> command, Argument... args) {
        CommandContext<T> commandContext = new GenericCommandContext<>(target);
        return command.execute(commandContext, args);
    }

    @Override
    public <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command, Argument... args) {
        ProducerCommandContext<T, V> commandContext = new GenericProducerCommandContext<>(target);
        return command.execute(commandContext, args);
    }
}
