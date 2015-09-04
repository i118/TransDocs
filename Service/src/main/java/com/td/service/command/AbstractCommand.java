package com.td.service.command;

import com.td.service.command.argument.Argument;
import com.td.service.command.exception.CommandException;
import com.td.service.command.exception.RequireArgumentException;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by zerotul.
 */
public abstract class AbstractCommand<T> implements Command<T>{

    Logger logger = Logger.getLogger(getClass());

    public CommandContext<T> execute(CommandContext<T> context, Argument... args){
        try{
            Map<String, Argument> argumentMap = buildArgumentsMap(args);
            checkArguments(argumentMap);
            return executeInternal(context, argumentMap);
        }catch (Exception e){
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            logger.error(writer.toString());
            context.setResult(Result.FAILURE);
            context.setException(new CommandException(e));
            return context;
        }
    }

    protected abstract CommandContext<T> executeInternal(CommandContext<T> context, Map<String, Argument> args) throws Exception;

    protected Set<String> requireArguments(){
        return Collections.EMPTY_SET;
    }

    private Map<String, Argument> buildArgumentsMap(Argument... args){
        Map<String, Argument> argumentMap = new HashMap<>();
        for (Argument arg : args){
            argumentMap.put(arg.getName(), arg);
        }
        return argumentMap;
    }

    private void checkArguments(Map<String, Argument> args){
        if(requireArguments()==null)return;
        requireArguments().forEach((String arg) -> {
            if (args.get(arg) == null) {
                throw new RequireArgumentException("argument " + arg + " in command " + getClass().getName() + " can not be null");
            }
        });
    }

    protected <T> T getArgumentValue(String name, Map<String, Argument> args){
        Argument<T> arg = args.get(name);
        return arg.getValue();
    }
}
