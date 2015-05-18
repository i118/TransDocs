package com.td.model.validation.annotation;

import com.td.model.validation.validator.UserLoginValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by konstantinchipunov on 06.08.14.
 */
@Retention(RUNTIME)
@Target( {TYPE})
@Constraint(validatedBy = UserLoginValidator.class)
public @interface UniqueLogin {
    String message() default "{user.login.doubleLogin}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
