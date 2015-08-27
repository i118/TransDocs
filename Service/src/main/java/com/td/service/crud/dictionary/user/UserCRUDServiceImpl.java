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
import com.td.service.crud.dictionary.role.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 09.11.13
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
@Service
@UserCrud
public class UserCRUDServiceImpl extends GenericCRUDService<UserModel> implements UserCRUDService {

    private RoleService roleService;

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

    @Transactional(readOnly = true)
    public UserModel getUserByName(String userName, LazyInitVisiter<UserModel> lazyInitVisiter) {
        UserModel userModel = getUserByName(userName);
        if (userModel != null && lazyInitVisiter != null) {
            lazyInitVisiter.initLazy(userModel);
        }
        return userModel;
    }

    @Override
    public void save(UserModel persistent) {
        super.save(persistent);
    }

    @Override
    protected UserRepository getRepository() {
        return (UserRepository) super.getRepository();
    }

    private void createUser(UserModel model, String password) {
        model.setPassword(new Password(password, model));
        IUserModel currentUser = getCurrentUser();
        model.setCompany(currentUser.getCompany());
        saveOrUpdate(model);

    }

    @Override
    @Transactional(readOnly = true)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(filterObject, 'CreateUserAction')")
    public void createDictionaryObject(UserModel object, Map<String, String> args) {
        final String password = args.get(ArgumentName.PASSWORD);
        createUser(object, password);
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(filterObject, 'DeleteUserAction')")
    public void deleteDictionaryObject(UserModel object, Map<String, String> args) {
        deleteUser(object);
    }

    @Override
    @Transactional
    @PreAuthorize("hasPermission(filterObject, 'UpdateUserAction')")
    public void updateDictionaryObject(UserModel object, Map<String, String> args) {
        updateUser(object, args);
    }

    private void deleteUser(UserModel userModel) {
        delete(userModel);
    }


    private void updateUser(UserModel model, Map<String, String> args) {
        String password = args.get(ArgumentName.PASSWORD);
        if (password != null && !password.equals("")) {
            model.setPassword(new Password(password, model));
        }
        getRepository().saveOrUpdate(model);
    }

    public RoleService getRoleService() {
        return roleService;
    }

    @Inject
    @RoleServiceQualifier
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
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
