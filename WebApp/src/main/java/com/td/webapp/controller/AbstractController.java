package com.td.webapp.controller;

import com.td.service.command.argument.Argument;
import com.td.service.command.argument.ArgumentImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantinchipunov on 02.01.14.
 */
public abstract class AbstractController {

    public static final String CONTENT_TYPE = "Content-Type=application/json;charset=UTF-8";

    public static class RequestName{
        public static final String CREATE_OBJECT = "create.object";

        public static final String UPDATE_OBJECT = "update.object";

        public static final String DELETE_OBJECT = "delete.object";

        public static final String GET_OBJECT = "get.object";

    }

    public static class RequestParams{

    }

    public static class Header{
        public static final String CONTENT_DISPOSITION = "Content-Disposition";
    }

    public static class ArgumentName{}

    public abstract String getControllerName();

    protected Argument[] obtainArguments(Map<String, String> args){
        if(args==null) return new Argument[0];
        List<Argument> argumentList = new ArrayList<>();
        args.forEach((String key, Object value)->{
            argumentList.add(ArgumentImpl.ArgumentBuilder.with(key).valueOf(value));
        });
        return argumentList.toArray(new Argument[0]);
    }
}
