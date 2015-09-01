package com.td.service.command;

import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zerotul.
 */
public abstract class AbstractProducerCommand<T, V> implements ProducerCommand<T, V>{
    Logger logger = Logger.getLogger(getClass());

    @Override
    public ProducerCommandContext<T, V> execute(ProducerCommandContext<T, V> context) {
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

    protected abstract ProducerCommandContext<T, V> executeInternal(ProducerCommandContext<T, V> context) throws Exception;
}
