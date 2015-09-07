package com.td.service.command.crud.user;

import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.Command;
import com.td.service.command.CommandService;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.UpdateCommand;
import com.td.service.command.crud.qualifier.UpdateUser;
import com.td.service.command.qualifier.ApplyChangesQualifier;
import com.td.service.context.qualifier.CommandServiceQualifier;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by zerotul.
 */
@Component
@UpdateUser
public class UpdateUserCommand extends UpdateCommand<UserDTO, UserModel> {


    public static class Arguments extends UpdateCommand.Arguments{
        public static final String PASSWORD = "password";
    }

    @Inject
    public UpdateUserCommand(@ApplyChangesQualifier Command<ModelDTO> applyChanges, @CommandServiceQualifier CommandService commandService) {
        super(applyChanges, commandService);
    }

    @Override
    protected ProducerCommandContext<UserDTO, UserModel> executeInternal(ProducerCommandContext<UserDTO, UserModel> context, Map<String, Argument> args) throws Exception {
        context = super.executeInternal(context, args);
        if(context.isFailure())throw context.getException();

        String password = getArgumentValue(Arguments.PASSWORD,args);
        UserModel user = context.getProduced();
        if (password != null && !password.equals("")) {
            user.setPassword(new Password(password, user));
        }
        return context;
    }


}
