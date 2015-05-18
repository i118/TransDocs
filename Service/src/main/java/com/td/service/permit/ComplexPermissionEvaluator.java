package com.td.service.permit;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by konstantinchipunov on 01.08.14.
 */
public class ComplexPermissionEvaluator implements PermissionEvaluator, PermissionEvaluatorAdapter {

    private PermissionActionEvaluatorFactory evaluatorFactory;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        PermissionEvaluatorAdapter evaluator = evaluatorFactory.getEvaluator(permission);
        if(evaluator!=null){
            return evaluator.hasPermission(authentication, targetDomainObject, permission);
        }
       return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        PermissionEvaluatorAdapter evaluator = evaluatorFactory.getEvaluator(permission);
        if(evaluator!=null){
            return evaluator.hasPermission(authentication, targetId, targetType, permission);
        }
        return true;
    }

    public PermissionActionEvaluatorFactory getEvaluatorFactory() {
        return evaluatorFactory;
    }

    @Inject
    public void setEvaluatorFactory(PermissionActionEvaluatorFactory evaluatorFactory) {
        this.evaluatorFactory = evaluatorFactory;
    }
}
