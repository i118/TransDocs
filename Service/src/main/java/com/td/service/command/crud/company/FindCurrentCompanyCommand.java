package com.td.service.command.crud.company;

import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.service.command.AbstractProducerCommand;
import com.td.service.command.ProducerCommandContext;
import com.td.service.command.argument.Argument;
import com.td.service.command.crud.qualifier.FindCompanyBy;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.crud.dictionary.company.CompanyService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by zerotul.
 */
@Component
@FindCompanyBy(findBy = FindCompanyBy.FindBy.CURRENT)
public class FindCurrentCompanyCommand extends AbstractProducerCommand<Object, CompanyModel> {

    private final CompanyService companyService;

    @Inject
    public FindCurrentCompanyCommand(@CompanyCrud CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    protected ProducerCommandContext<Object, CompanyModel> executeInternal(ProducerCommandContext<Object, CompanyModel> context, Map<String, Argument> args) throws Exception {
        CompanyModel companyModel = companyService.getCurrentCompany();
        context.setProduced(companyModel);
        return context;
    }
}
