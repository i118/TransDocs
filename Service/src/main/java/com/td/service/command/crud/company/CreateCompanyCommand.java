package com.td.service.command.crud.company;

import com.td.model.configuration.CompanyInstaller;
import com.td.model.context.qualifier.CompanyInstallQualifier;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.validation.ValidationService;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.CommandArguments;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.CRUDCompanyCommand;
import com.td.service.command.crud.qualifier.CRUDType;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.context.qualifier.UserCRUDFacade;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.dictionary.company.CompanyService;
import com.td.service.crud.dictionary.role.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by zerotul.
 */
@Component
@CRUDCompanyCommand(CRUDType.CREATE)
public class CreateCompanyCommand extends AbstractProducerCommand<CompanyModel, CompanyModel> {

    private final CompanyService companyService;

    private final CompanyInstaller companyInstaller;

    private final CRUDFacade<UserModel, UserDTO> userFacade;

    private final RoleService roleService;


    @Inject
    public CreateCompanyCommand(@CompanyCrud CompanyService companyService,
                                @CompanyInstallQualifier CompanyInstaller companyInstaller,
                                @UserCRUDFacade CRUDFacade<UserModel, UserDTO> userFacade,
                                @RoleServiceQualifier RoleService roleService) {
        this.companyService = companyService;
        this.companyInstaller = companyInstaller;
        this.userFacade = userFacade;
        this.roleService = roleService;
    }

    @Override
    @PreAuthorize("hasAnyRole('"+ RoleNames.ROLE_SUPER_ADMIN+"')")
    protected ProducerCommandContext<CompanyModel, CompanyModel> executeInternal(ProducerCommandContext<CompanyModel, CompanyModel> context, Map<String, Argument> args) throws Exception {
        CompanyModel company = context.getTarget();
        ValidationService.getInstance().validate(company);
        companyInstaller.install(company);
        companyService.save(company);
        UserModel userModel = new UserModel();
        userModel.setLogin("admin");
        userModel.setFirstName("admin");
        userModel.setLastName("admin");
        userModel.setGender(Person.Gender.MAN);
        userModel.setCompany(company);
        RoleModel roleModel = roleService.getRoleByName(RoleNames.ROLE_ADMIN);
        userModel.addRoleModel(roleModel);
        userFacade.create(userModel, CommandArguments.User.PASSWORD.valueOf("admin"));
        return context;
    }

}
