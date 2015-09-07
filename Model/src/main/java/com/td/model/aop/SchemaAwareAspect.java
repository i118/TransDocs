package com.td.model.aop;

import com.td.model.annotation.SchemaAware;
import com.td.model.context.qualifier.CompanyQualifier;
import com.td.model.repository.dictionary.CompanyRepository;
import com.td.model.entity.dictionary.company.CompanyModel;
import com.td.model.multitenant.SchemaProviderImpl;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by zerotul on 03.04.15.
 */
@Aspect
public class SchemaAwareAspect {

    Logger logger = Logger.getLogger(getClass());

    @Pointcut("@annotation(schemaAware) && within(com.td..*+)")
    public void schemaAwareAnnotation(SchemaAware schemaAware){}

    private static class SchemaAwareAspectHolder {
        static final SchemaAwareAspect instance = new SchemaAwareAspect();
    }

    public static SchemaAwareAspect aspectOf(){
        return SchemaAwareAspectHolder.instance;
    }

    @Autowired
    @CompanyQualifier
    private CompanyRepository companyDao;

    @Around(value = "schemaAwareAnnotation(schemaAware)")
    public Object schemaAware(ProceedingJoinPoint pjp, SchemaAware schemaAware) throws Throwable {
        if(pjp.getSignature().getDeclaringType().isInterface())return pjp.proceed();

        logger.debug("schema aware start");
        Objects.requireNonNull(schemaAware);
        String oldSchema = SchemaProviderImpl.currentSchema();
        logger.debug("schema aware: old schema="+oldSchema);
        logger.debug("schema aware: resolved by="+schemaAware.resolvedBy());
        try {
            CompanyModel companyModel = findCompany(pjp, schemaAware);
            if (companyModel != null) {
                SchemaProviderImpl.setCurrentSchema("td_" + companyModel.getLogin());
                logger.debug("schema aware: new schema=" + SchemaProviderImpl.currentSchema());
            }
            return pjp.proceed();
        }finally {
            SchemaProviderImpl.setCurrentSchema(oldSchema);
        }

    }

    private CompanyModel findCompany(ProceedingJoinPoint pjp, final SchemaAware schemaAware){
        SchemaAware.ResolvedBy resolvedBy = schemaAware.resolvedBy();
        Object[] args = pjp.getArgs();
        if(SchemaAware.ResolvedBy.COMPANY.equals(resolvedBy)) {
            for (Object obj : args) {
                if (obj instanceof CompanyModel) {
                    return (CompanyModel) obj;
                }
            }
        }else if(SchemaAware.ResolvedBy.COMPANY_ID.equals(resolvedBy)){
            for (Object obj : args) {
                if (obj instanceof UUID) {
                  return getCompanyById((UUID) obj);
                }
            }
        }

        return null;
    }

    @Transactional
    private CompanyModel getCompanyById(UUID id){
        return companyDao.findById(id);
    }
}
