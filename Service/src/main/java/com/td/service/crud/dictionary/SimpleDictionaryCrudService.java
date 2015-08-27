package com.td.service.crud.dictionary;

import com.td.model.context.qualifier.DictionaryQualifier;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.DictionaryRepository;
import com.td.model.entity.dictionary.SimpleDictionary;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.role.RoleNames;
import com.td.model.utils.PagingList;
import com.td.service.context.qualifier.DictionaryCrud;
import com.td.service.context.qualifier.UserCrud;
import com.td.service.crud.GenericCRUDService;
import com.td.service.crud.dictionary.user.UserCRUDService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.Map;

/**
 * Created by zerotul on 08.04.15.
 */
@Service
@DictionaryCrud
public class SimpleDictionaryCrudService extends GenericCRUDService<SimpleDictionary> implements DictionaryCRUDService<SimpleDictionary> {

    private static final String PRE_AUTHORIZE = "hasAnyRole('"+ RoleNames.ROLE_SUPER_ADMIN+","+RoleNames.ROLE_ADMIN+","+RoleNames.ROLE_MANAGER+"')";

    @Inject
    @UserCrud
    private UserCRUDService userCRUDService;

    @Inject
    public SimpleDictionaryCrudService(@DictionaryQualifier DictionaryRepository<SimpleDictionary> dao) {
        super(dao);
    }


    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void createDictionaryObject(SimpleDictionary object, Map<String, String> args) {
        object.setOwner(userCRUDService.getCurrentUser());
        getRepository().saveOrUpdate(object);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void deleteDictionaryObject(SimpleDictionary object, Map<String, String> args) {
       getRepository().delete(object);
    }

    @Override
    @PreAuthorize(PRE_AUTHORIZE)
    @Transactional(rollbackFor = Throwable.class)
    public void updateDictionaryObject(SimpleDictionary object, Map<String, String> args) {
       getRepository().saveOrUpdate(object);
    }

    @Override
    protected DictionaryRepository<SimpleDictionary> getRepository() {
        return (DictionaryRepository<SimpleDictionary>) super.getRepository();
    }
}
