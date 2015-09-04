package com.td.service.command;

import com.td.model.entity.Model;
import com.td.service.command.argument.ArgumentImpl.ArgumentBuilder;
import com.td.service.command.crud.UpdateCommand;
import com.td.service.command.crud.user.CreateUserCommand;
import com.td.service.command.map.ApplyChangesToEntityCommand;
import com.td.service.crud.CRUDService;

/**
 * Created by zerotul.
 */
public abstract class CommandArguments {

    public static class ApplyChanges {
        public static final ArgumentBuilder<Model> DESTINATION = ArgumentBuilder.with(ApplyChangesToEntityCommand.Arguments.DESTINATION);
    }

    public static class CRUD {
        public static final ArgumentBuilder<CRUDService> CRUD_SERVICE = ArgumentBuilder.with(UpdateCommand.Arguments.CRUD_SERVICE);
    }

    public static class User{
        public static final ArgumentBuilder<String> PASSWORD = ArgumentBuilder.with(CreateUserCommand.Arguments.PASSWORD);
    }
}
