package com.td.service.crud.dictionary.user;

import com.td.model.context.qualifier.SecurityQualifier;
import com.td.model.context.qualifier.UserQualifier;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.utils.PagingList;
import com.td.model.security.SecurityService;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.LazyInitVisiter;
import com.td.service.crud.dictionary.GenericDictionaryCRUDService;
import com.td.service.crud.dictionary.role.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 09.11.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
@Service
@UserCrud
public class UserCRUDServiceImpl extends GenericDictionaryCRUDService<UserModel> implements UserCRUDService {

    private SecurityService securityService;

    @Inject
    public UserCRUDServiceImpl(@UserQualifier UserRepository repository) {
        super(repository);
    }

    public static class ArgumentName extends GenericCRUDService.ArgumentName {
        public static final String PASSWORD = "password";
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel getUserByName(String userName) {
        return getRepository().getUserByName(userName);
    }

    @Override
    public UserModel getCurrentUser() {
        return securityService.getCurrentUser();
    }

    @Override
    public List<UserModel> findByCompanyId(UUID companyId) {
        return getRepository().findByCompanyId(companyId);
    }


    @Override
    protected UserRepository getRepository() {
        return (UserRepository) super.getRepository();
    }



    public SecurityService getSecurityService() {
        return securityService;
    }

    @Inject
    @SecurityQualifier
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
