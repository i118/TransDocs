package com.td.model.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by konstantinchipunov on 07.08.14.
 */
public class ValidationService {

    public static volatile ValidationService validationService;

    private ValidationService(){}

    public static ValidationService getInstance(){
        if(validationService==null){
            synchronized (ValidationService.class){
                if(validationService==null){
                    validationService = new ValidationService();
                }
            }
        }
       return validationService;
    }


    public void validate(Object object, Class[] groups) {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(constraintViolations));
        }

    }

    public void validate(Object object) {
       validate(object, new Class[]{});
    }
}
