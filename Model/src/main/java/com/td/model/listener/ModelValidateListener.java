package com.td.model.listener;

import com.td.model.entity.Persistent;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 05.08.14.
 */
public class ModelValidateListener {

    @PrePersist
    public void persist(Persistent persistent){
        validate(persistent);
    }

    @PreUpdate
    public void update(Persistent persistent){
        validate(persistent);
    }

    protected void validate(Persistent persistent) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(persistent);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolations));
        }

    }
}
