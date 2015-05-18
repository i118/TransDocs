package com.td.model.validation.validator;

import com.td.model.context.qualifier.UserQualifier;
import com.td.model.repository.dictionary.user.UserRepository;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.validation.annotation.UniqueLogin;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by konstantinchipunov on 06.08.14.
 */
public class UserLoginValidator implements ConstraintValidator<UniqueLogin, IUserModel> {

    private UserRepository userRepository;

    @Override
    public void initialize(UniqueLogin constraintAnnotation) {

    }

    @Override
    public boolean isValid(IUserModel userModel, ConstraintValidatorContext context) {
        if(userRepository==null) return false;
        return !userRepository.hasUser(userModel);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Inject
    @UserQualifier
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
