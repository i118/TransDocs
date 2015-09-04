package com.td.service.command.crud.user;

import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindUserBy;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul.
 */
@Component
@FindUserBy(findBy = FindUserBy.FindBy.COMPANY_ID)
public class FindUserByCompanyCommand extends AbstractProducerCommand<UUID, List<UserModel>> {

    private UserCRUDService userCRUDService;

    @Inject
    public FindUserByCompanyCommand(@UserCrud UserCRUDService userCRUDService) {
        this.userCRUDService = userCRUDService;
    }

    @Override
    protected ProducerCommandContext<UUID, List<UserModel>> executeInternal(ProducerCommandContext<UUID, List<UserModel>> context, Map<String, Argument> args) throws Exception {
        context.setProduced(userCRUDService.findByCompanyId(context.getTarget()));
        return context;
    }
}
