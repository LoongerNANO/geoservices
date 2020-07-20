package com.esrichina.geoservices.annotation;


import com.esrichina.geoservices.validator.StateValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StateValueValidator.class)
public @interface StateAnnotation {

    String message() default "";
    String value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

