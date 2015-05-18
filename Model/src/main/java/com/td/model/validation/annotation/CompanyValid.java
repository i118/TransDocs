package com.td.model.validation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zerotul on 30.03.15.
 */
@Retention(RUNTIME)
@Target( {TYPE})
@Constraint(validatedBy = {})
@NotEmpty.List({
        @NotEmpty(message = "{company.login.notnull}", fieldName = "login")
})
public @interface CompanyValid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };
}
