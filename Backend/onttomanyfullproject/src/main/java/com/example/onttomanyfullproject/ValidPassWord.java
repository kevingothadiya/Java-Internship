package com.example.onttomanyfullproject;

import com.example.onttomanyfullproject.custom.Validator.PassWordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PassWordValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassWord {
    String message() default "Password must contain uppercase, lowercase, number and special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
