package com.td.model.repository.dictionary.role;

import com.td.model.repository.dictionary.DictionaryJPARepository;
import com.td.model.entity.dictionary.role.IRoleModel;
import com.td.model.entity.dictionary.role.RoleModel;
import com.td.model.entity.dictionary.role.RoleModel_;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 21.11.13
 * Time: 18:02
 * To change this template use File | Settings | File Templates.
 */
public class RoleJPARepository extends DictionaryJPARepository<RoleModel> implements RoleRepository {
    public RoleJPARepository() {
        super(RoleModel.class);
    }

    @Override
    public RoleModel getRoleByName(String roleName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<RoleModel> query = cb.createQuery(getModelClass());
        Root<RoleModel> root = query.from(getModelClass());
        Predicate condition = cb.equal(root.get(RoleModel_.roleName), roleName);
        Predicate deleteCondition  = cb.equal(root.get(RoleModel_.deleted), false);
        query.where(condition, deleteCondition);
        try {
            return getEntityManager().createQuery(query).setMaxResults(1).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void delete(RoleModel persistent) {
        throw new UnsupportedOperationException();
    }
}
