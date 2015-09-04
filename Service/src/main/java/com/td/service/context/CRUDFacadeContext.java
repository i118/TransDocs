package com.td.service.context;

import com.td.model.context.qualifier.DocumentQualifier;
import com.td.model.dto.ModelDTO;
import com.td.model.dto.dictionary.SimpleDictionaryDTO;
import com.td.model.dto.dictionary.contractor.CarrierDTO;
import com.td.model.dto.dictionary.contractor.CompanyDTO;
import com.td.model.dto.dictionary.contractor.CustomerDTO;
import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.dto.document.OrderDocumentDTO;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.model.entity.dictionary.company.CarrierModel;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.company.CustomerModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.entity.document.OrderDocumentModel;
import com.td.service.command.Command;
import com.td.service.command.CommandService;
import com.td.service.command.ProducerCommand;
import com.td.service.command.crud.qualifier.*;
import com.td.service.context.qualifier.*;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.CRUDService;
import com.td.service.crud.CommandCRUDFacade;
import com.td.service.crud.dictionary.company.CompanyService;
import com.td.service.crud.document.DocumentCRUDService;
import com.td.service.crud.document.DocumentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

/**
 * Created by zerotul.
 */
@Configuration
public class CRUDFacadeContext {

    @Inject
    @ContractorCrud(ContractorCrud.Type.CUSTOMER)
    private CRUDService<CustomerModel> customerService;

    @Inject
    @ContractorCrud(ContractorCrud.Type.CARRIER)
    private CRUDService<CarrierModel> carrierService;

    @Inject
    @UserCrud
    private CRUDService<UserModel> userService;

    @Inject
    @DictionaryCrud
    private CRUDService<SimpleDictionary> dictionaryService;

    @Inject
    @CompanyCrud
    private CompanyService companyService;

    @Inject
    @DocumentQualifier(DocumentQualifier.Type.ORDER)
    private DocumentService<OrderDocumentModel> orderDocumentService;

    @Inject
    @CreateCommandQualifier
    private ProducerCommand<Persistent, Persistent> createCommand;

    @Inject
    @UpdateCommandQualifier
    private ProducerCommand<ModelDTO, Persistent> updateCommand;

    @Inject
    @DeleteCommandQualifier
    private Command<Persistent> deleteCommand;

    @Inject
    @CommandServiceQualifier
    private CommandService commandService;

    @Inject
    @CreateUser
    private ProducerCommand<UserModel, UserModel> createUserCommand;

    @Inject
    @UpdateUser
    private ProducerCommand<UserDTO, UserModel> updateUserCommand;


    @Bean
    @ContractorCRUDFacade(ContractorCRUDFacade.Type.CUSTOMER)
    public CRUDFacade<CustomerModel, CustomerDTO> customerCommandCrudFacade(){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade<>(customerService, commandService);
        crudFacade.setCreateCommand(createCommand);
        crudFacade.setUpdateCommand(updateCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }


    @Bean
    @ContractorCRUDFacade(ContractorCRUDFacade.Type.CARRIER)
    public CRUDFacade<CarrierModel, CarrierDTO> carrierCommandCrudFacade(){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade<>(carrierService, commandService);
        crudFacade.setCreateCommand(createCommand);
        crudFacade.setUpdateCommand(updateCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }

    @Bean
    @UserCRUDFacade
    public CRUDFacade<UserModel, UserDTO> userCommandCrudFacade(){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade<>(userService, commandService);
        crudFacade.setCreateCommand(createUserCommand);
        crudFacade.setUpdateCommand(updateUserCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }

    @Bean
    @DictionaryCRUDFacade
    public CRUDFacade<SimpleDictionary, SimpleDictionaryDTO> dictionaryCRUDFacade(){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade<>(dictionaryService, commandService);
        crudFacade.setCreateCommand(createCommand);
        crudFacade.setUpdateCommand(updateCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }

    @Bean
    @Inject
    @CompanyCRUDFacade
    public CRUDFacade<CompanyModel, CompanyDTO> companyCRUDFacade(@CRUDCompanyCommand(CRUDType.CREATE) ProducerCommand<CompanyModel, CompanyModel> createCompany){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade(companyService, commandService);
        crudFacade.setCreateCommand(createCompany);
        crudFacade.setUpdateCommand(updateCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }

    @Bean
    @Inject
    @OrderCRUDFacade
    public CRUDFacade<OrderDocumentModel, OrderDocumentDTO> orderDocumentCRUDFacade(){
        CommandCRUDFacade crudFacade = new CommandCRUDFacade(orderDocumentService, commandService);
        crudFacade.setCreateCommand(createCommand);
        crudFacade.setUpdateCommand(updateCommand);
        crudFacade.setDeleteCommand(deleteCommand);
        return crudFacade;
    }
}
