package com.furnitureshop.user.validation.validator;

import com.furnitureshop.common.util.ValidatorUtils;
import com.furnitureshop.user.service.UserService;
import com.furnitureshop.user.validation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private String message;
    private final UserService service;

    public UniqueUsernameValidator(UserService userService) {
        service = userService;
    }

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
        message = uniqueUsername.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null)
            return false;

        boolean isTaken = service.isTakenUsername(username);

        if(!isTaken)
            return true;

        ValidatorUtils.addError(context, message);
        return false;
    }

}
