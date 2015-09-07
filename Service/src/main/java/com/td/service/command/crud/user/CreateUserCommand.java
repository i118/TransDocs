package com.td.service.command.crud.user;

import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.CreateUser;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by zerotul.
 */
@Component
@CreateUser
public class CreateUserCommand extends AbstractProducerCommand<UserModel, UserModel>{

    private final  UserCRUDService crudService;

    public static class Arguments{
        public static final String PASSWORD = "password";
    }

    @Inject
    public CreateUserCommand(@UserCrud UserCRUDService crudService) {
        this.crudService = crudService;
    }

    @Override
    protected ProducerCommandContext<UserModel, UserModel> executeInternal(ProducerCommandContext<UserModel, UserModel> context, Map<String, Argument> args) throws Exception {
        UserModel user = context.getTarget();
        user.setPassword(new Password(getArgumentValue(Arguments.PASSWORD, args), user));
        IUserModel currentUser = crudService.getCurrentUser();
        user.setCompany(currentUser.getCompany());
        crudService.saveOrUpdate(user);
        context.setProduced(user);
        return context;
    }


    @Override
    protected Set<String> requireArguments() {
        return Collections.singleton(Arguments.PASSWORD);
    }
}
