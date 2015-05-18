package com.td.model.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by konstantinchipunov on 07.08.14.
 */
@Retention(RUNTIME)
@Target( {TYPE})
@Constraint(validatedBy = {})
@NotEmpty.List({
        @NotEmpty(message = "{user.firstName.notnull}", fieldName = "firstName"),
        @NotEmpty(message = "{user.lastName.notnull}", fieldName = "lastName"),
        @NotEmpty(message = "{user.gender.notnull}", fieldName = "gender"),
        @NotEmpty(message = "{user.login.notnull}", fieldName = "login"),
        @NotEmpty(message = "{user.password.notnull}", fieldName = "password")
})
public @interface UserValid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}
