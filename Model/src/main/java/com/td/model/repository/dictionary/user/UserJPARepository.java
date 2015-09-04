package com.td.model.repository.dictionary.user;

import com.td.model.repository.dictionary.DictionaryJPARepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.model.entity.dictionary.user.UserModel_;

import javax.persistence.EntityGraph;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: konstantinchipunov
 * Date: 07.11.13
 * Time: 0:13
 * To change this template use File | Settings | File Templates.
 *
 * Дао модели пользователя
 */
public class UserJPARepository extends DictionaryJPARepository<UserModel> implements UserRepository {

    public UserJPARepository() {
        super(UserModel.class);
    }

    

    @Override
    public UserModel getUserByName(String userName) {
       return getUserByName(userName, null, false);
    }

    public UserModel getUserByName(String userName, FlushModeType flushMode, boolean isShowDeleted) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UserModel> query = cb.createQuery(getModelClass());
        Root<UserModel> root = query.from(getModelClass());
        Predicate loginCondition = cb.equal(root.get(UserModel_.login), userName);
        if(!isShowDeleted) {
            Predicate deleteCondition = cb.equal(root.get(UserModel_.deleted), isShowDeleted);
            query.where(loginCondition, deleteCondition);
        }else{
            query.where(loginCondition);
        }
        try {
            TypedQuery<UserModel> typedQuery = getEntityManager().createQuery(query);
            if(flushMode!=null) {
                typedQuery.setFlushMode(flushMode);
            }
            return typedQuery.setMaxResults(1).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public boolean hasUser(IUserModel userModel){
       IUserModel user = getUserByName(userModel.getLogin(), FlushModeType.COMMIT, true);
       return user!=null;
    }

    @Override
    public List<UserModel> findByCompanyId(UUID companyId) {
        TypedQuery<UserModel> query = getEntityManager().createNamedQuery(UserModel.Query.FIND_BY_COMPANY, UserModel.class);
        query.setParameter("companyId", companyId);
        return query.getResultList();
    }


}
