package com.td.service.command;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zerotul.
 */
public abstract class AbstractCommand<T> implements Command<T>{

    Logger logger = Logger.getLogger(getClass());

    public CommandContext<T> execute(CommandContext<T> context){
        try{
            return executeInternal(context);
        }catch (Exception e){
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            logger.error(writer.toString());
            context.setResult(Result.FAILURE);
            context.setException(e);
            return context;
        }
    }

    protected abstract CommandContext<T> executeInternal(CommandContext<T> context) throws Exception;
}
