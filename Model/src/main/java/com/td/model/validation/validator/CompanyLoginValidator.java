package com.td.model.validation.validator;

import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.validation.annotation.UniqueCompany;


import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by zerotul on 30.03.15.
 */
public class CompanyLoginValidator implements ConstraintValidator<UniqueCompany, CompanyModel> {

    private CompanyRepository companyDao;

    @Override
    public void initialize(UniqueCompany constraintAnnotation) {

    }

    @Override
    public boolean isValid(CompanyModel value, ConstraintValidatorContext context) {
//        if(value.getLogin()==null)return true;
//        CompanyModel companyModel = companyDao.findByLogin(value.getLogin());
//        return companyModel == null;
        return true;
    }

    public CompanyRepository getCompanyDao() {
        return companyDao;
    }

    @Inject
    @CompanyQualifier
    public void setCompanyDao(CompanyRepository companyDao) {
        this.companyDao = companyDao;
    }
}
