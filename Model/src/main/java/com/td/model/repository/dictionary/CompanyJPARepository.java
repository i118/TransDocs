package com.td.model.repository.dictionary;

import com.td.model.entity.dictionary.company.CompanyModel;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by zerotul on 25.03.15.
 */
public class CompanyJPARepository extends DictionaryJPARepository<CompanyModel> implements CompanyRepository {


    public CompanyJPARepository() {
        super(CompanyModel.class);
    }

    @Override
    public CompanyModel findByLogin(String login) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CompanyModel> crit = cb.createQuery(getModelClass());
        Root<CompanyModel> root = crit.from(getModelClass());
        crit.where(cb.equal(root.get("login"), login));
        TypedQuery<CompanyModel> typedQuery = getEntityManager().createQuery(crit);
        typedQuery.setFlushMode(FlushModeType.COMMIT);
        try {
            return typedQuery.setMaxResults(1).getSingleResult();
        }catch(NoResultException e){
           return null;
        }
    }
}
