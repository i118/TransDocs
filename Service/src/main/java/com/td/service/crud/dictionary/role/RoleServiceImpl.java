package com.td.service.crud.dictionary.role;

import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.utils.PagingList;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.crud.AbstractCRUDService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerotul.specification.Specification;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
@Service
@RoleServiceQualifier
public class RoleServiceImpl extends AbstractCRUDService<IRoleModel, RoleRepository> implements RoleService {

    @Inject
    public RoleServiceImpl(@RoleQualifier RoleRepository dao) {
        super(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    public void createDictionaryObject(IRoleModel object, Map<String, String> args) {

    }

    @Override
    public void deleteDictionaryObject(IRoleModel object, Map<String, String> args) {

    }

    @Override
    public void updateDictionaryObject(IRoleModel object, Map<String, String> args) {

    }

    @Override
    public boolean hasRole(IUserModel userModel, String roleName) {
      Optional<IRoleModel> roleOptional = userModel.getRoleModels().parallelStream().filter((IRoleModel roleModel)->roleModel.getRoleName().equals(roleName)).findAny();
      return roleOptional.isPresent();
    }

    @Override
    public boolean hasAnyRole(IUserModel userModel, String... roleNames) {
        List<String> roleNameList = userModel.getRoleModels().stream()
                .map((IRoleModel roleModel) -> roleModel.getRoleName()).collect(Collectors.toList());
      for (String roleName: roleNames){
          if(roleNameList.contains(roleName)){
              return true;
          }
      }
      return false;
    }

    @Override
    @Transactional(readOnly = true)
    public IRoleModel getRoleByName(String roleName) {
        return repository.getRoleByName(roleName);
    }
}
