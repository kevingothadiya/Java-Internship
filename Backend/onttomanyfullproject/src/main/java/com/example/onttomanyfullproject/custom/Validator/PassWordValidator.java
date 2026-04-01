package com.example.onttomanyfullproject.custom.Validator;

import com.example.onttomanyfullproject.ValidPassWord;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PassWordValidator implements ConstraintValidator<ValidPassWord,String> {
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null) {
            return false;
        }

        return password.matches(PASSWORD_REGEX);
    }
}
