
package com.pershyn.electronicshop.validation;

import com.pershyn.electronicshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//
@Component
public class UserFormValidation implements Validator {

    @Qualifier("emailValidator")
    private final MailFormValidation mailFormValidation;

    @Autowired
    public UserFormValidation(MailFormValidation mailFormValidation) {
      this.mailFormValidation = mailFormValidation;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.user.login");
        ValidationUtils.rejectIfEmpty(errors, "password", "empty.user.password");
        ValidationUtils.rejectIfEmpty(errors, "email", "empty.user.email");
        ValidationUtils.rejectIfEmpty(errors, "passwordConfirmation", "no_match.password");

        if (!user.getLogin().trim().equals("") &&
                user.getLogin().trim().length() < 3 || user.getLogin().trim().length() > 32) {
            errors.rejectValue("login", "size.user.login");
        }
        if (!user.getPassword().trim().equals("") &&
                user.getPassword().trim().length() < 6 || user.getPassword().trim().length() > 32) {
            errors.rejectValue("password", "size.user.password");
        }
        if (!(user.getPassword().equals(user.getPasswordConfirmation()) ) )
        {
            errors.rejectValue("passwordConfirmation", "no_match.password");

        }
        if (!mailFormValidation.valid(user.getEmail())) {
            errors.rejectValue("email", "invalid.user.email");
        }
    }
}

