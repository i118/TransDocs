package com.td.service.command;

import com.td.service.context.qualifier.CommandServiceQualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zerotul.
 */
@Service
@CommandServiceQualifier
public class CommandServiceImpl implements CommandService {

    @Override
    public <T> CommandContext<T> execute(T target, Command<T> command) {
        return execute(target, command, null);
    }


    @Override
    public <T> CommandContext<T> execute(T target, Command<T> command, Map<String, Object> arguments) {
        CommandContext<T> commandContext = new GenericCommandContext<>(target);
        if(arguments!=null){
            arguments.forEach((String key, Object value)->{
                commandContext.addArgument(key, value);
            });
        }
        return command.execute(commandContext);
    }

    @Override
    public <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command) {
        return execute(target, command, null);
    }

    @Override
    public <T, V> ProducerCommandContext<T, V> execute(T target, ProducerCommand<T, V> command, Map<String, Object> arguments) {
        ProducerCommandContext<T, V> commandContext = new GenericProducerCommandContext<>(target);
        if(arguments!=null){
            arguments.forEach((String key, Object value)->{
                commandContext.addArgument(key, value);
            });
        }
        return command.execute(commandContext);
    }
}
