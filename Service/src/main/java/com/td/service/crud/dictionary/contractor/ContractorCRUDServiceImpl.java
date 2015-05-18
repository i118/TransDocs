package com.td.service.crud.dictionary.contractor;

import com.td.model.repository.dictionary.contractor.ContractorRepository;
import com.td.model.entity.dictionary.company.Contractor;
import com.td.model.entity.dictionary.company.JuridicalPerson;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.utils.PagingList;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.AbstractCRUDService;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by konstantinchipunov on 23.08.14.
 */
public class ContractorCRUDServiceImpl<T extends JuridicalPerson & Contractor, D extends ContractorRepository<T>> extends AbstractCRUDService<T, D> implements ContractorCRUDService<T, D> {

    protected static final String PRE_AUTHORIZE = "hasAnyRole('" + RoleNames.ROLE_SUPER_ADMIN + "," + RoleNames.ROLE_ADMIN + "," + RoleNames.ROLE_MANAGER + "')";

    @Inject
    @UserCrud
    private UserCRUDService userCRUDService;

    public ContractorCRUDServiceImpl(D dao) {
        super(dao);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(readOnly = true)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void createDictionaryObject(T object, Map<String, String> args) {
        object.setOwner(userCRUDService.getCurrentUser());
        getRepository().saveOrUpdate(object);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @PreAuthorize(PRE_AUTHORIZE)
    public void deleteDictionaryObject(T object, Map<String, String> args) {
        getRepository().delete(object);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void updateDictionaryObject(T object, Map<String, String> args) {
        getRepository().saveOrUpdate(object);
    }
}
