package com.td.service.crud.dictionary.company;

import com.td.model.annotation.SchemaAware;
import com.td.model.configuration.CompanyInstaller;
import com.td.model.context.qualifier.CompanyInstallQualifier;
import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.entity.Persistent;
import com.td.model.entity.dictionary.Person;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.utils.PagingList;
import com.td.model.validation.ValidationService;
import com.td.service.context.qualifier.CompanyCrud;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.dictionary.role.RoleService;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zerotul on 25.03.15.
 */
@Service
@CompanyCrud
public class CompanyCRUDService extends GenericCRUDService<CompanyModel> implements CompanyService{

    private static final String PRE_AUTHORIZE = "hasAnyRole('"+ RoleNames.ROLE_SUPER_ADMIN +"," + RoleNames.ROLE_ADMIN+", "+RoleNames.ROLE_MANAGER+"')";

    private UserCRUDService userService;

    private RoleService roleService;

    private CompanyInstaller companyInstaller;

    @Inject
    public CompanyCRUDService(@CompanyQualifier CompanyRepository dao, @CompanyInstallQualifier CompanyInstaller companyInstaller) {
        super(dao);
        this.companyInstaller = companyInstaller;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize(PRE_AUTHORIZE)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('"+ RoleNames.ROLE_SUPER_ADMIN+"')")
    public void createDictionaryObject(CompanyModel object, Map<String, String> args) {
        ValidationService.getInstance().validate(object);
        companyInstaller.install(object);
        getRepository().save(object);
        UserModel userModel = new UserModel();
        userModel.setLogin("admin");
        userModel.setPassword(new Password("admin", userModel));
        userModel.setFirstName("admin");
        userModel.setLastName("admin");
        userModel.setGender(Person.Gender.MAN);
        userModel.setCompany(object);
        RoleModel roleModel = roleService.getRoleByName(RoleNames.ROLE_ADMIN);
        userModel.addRoleModel(roleModel);
        userService.saveOrUpdate((UserModel) userModel);
    }

    @Override
    @Transactional
    @PreAuthorize(PRE_AUTHORIZE)
    public void deleteDictionaryObject(CompanyModel object, Map<String, String> args) {
        getRepository().delete(object);
    }

    @Override
    @Transactional
    public void updateDictionaryObject(CompanyModel object, Map<String, String> args) {
        ValidationService.getInstance().validate(object);
         getRepository().saveOrUpdate(object);
    }

    @Override
    @Transactional
    public CompanyModel getCurrentCompany() {
        return getUserService().getCurrentUser().getCompany();
    }

    public UserCRUDService getUserService() {
        return userService;
    }

    @Override
    @SchemaAware(resolvedBy = SchemaAware.ResolvedBy.COMPANY_ID)
    @PreAuthorize(PRE_AUTHORIZE)
    public CompanyModel findById(UUID id) {
        return super.findById(id);
    }

    @Override
    @SchemaAware(resolvedBy = SchemaAware.ResolvedBy.COMPANY_ID)
    @PreAuthorize(PRE_AUTHORIZE)
    public <D extends Persistent> D findById(UUID id, String typeName) {
        return super.findById(id, typeName);
    }

    @Override
    @SchemaAware(resolvedBy = SchemaAware.ResolvedBy.COMPANY_ID)
    @PreAuthorize(PRE_AUTHORIZE)
    public CompanyModel getReference(UUID id) {
        return super.getReference(id);
    }


    @Inject
    @UserCrud
    public void setUserService(UserCRUDService userService) {
        this.userService = userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    @Inject
    @RoleServiceQualifier
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected CompanyRepository getRepository() {
        return (CompanyRepository) super.getRepository();
    }
}
