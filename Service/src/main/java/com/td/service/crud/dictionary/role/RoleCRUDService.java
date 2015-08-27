package com.td.service.crud.dictionary.role;

import com.td.model.context.qualifier.RoleQualifier;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.repository.IRepository;
import com.td.model.repository.dictionary.role.RoleRepository;
import com.td.model.entity.dictionary.dataset.DictionaryDataSet;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.utils.PagingList;
import com.td.service.context.qualifier.RoleServiceQualifier;
import com.td.service.crud.GenericCRUDService;
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
public class RoleCRUDService extends GenericCRUDService<RoleModel> implements RoleService {

    @Inject
    public RoleCRUDService(@RoleQualifier RoleRepository dao) {
        super(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public <U extends DictionaryDataSet> PagingList<U> findDataSet(Specification<? super U> specification) {
        return getRepository().findDataSet(specification);
    }

    @Override
    public void createDictionaryObject(RoleModel object, Map<String, String> args) {

    }

    @Override
    public void deleteDictionaryObject(RoleModel object, Map<String, String> args) {

    }

    @Override
    public void updateDictionaryObject(RoleModel object, Map<String, String> args) {

    }

    @Override
    public boolean hasRole(UserModel userModel, String roleName) {
      Optional<RoleModel> roleOptional = userModel.getRoleModels().stream().filter((RoleModel roleModel)->roleModel.getRoleName().equals(roleName)).findAny();
      return roleOptional.isPresent();
    }

    @Override
    public boolean hasAnyRole(UserModel userModel, String... roleNames) {
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
    public RoleModel getRoleByName(String roleName) {
        return getRepository().getRoleByName(roleName);
    }

    @Override
    protected RoleRepository getRepository() {
        return (RoleRepository) super.getRepository();
    }
}
