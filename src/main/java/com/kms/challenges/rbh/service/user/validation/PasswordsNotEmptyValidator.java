package com.kms.challenges.rbh.service.user.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsNotEmptyValidator implements ConstraintValidator<PasswordsNotEmpty, Object> {

    private String passwordFieldName;
    private String passwordVerificationFieldName;

    @Override
    public void initialize(PasswordsNotEmpty constraintAnnotation) {
        passwordFieldName = constraintAnnotation.passwordFieldName();
        passwordVerificationFieldName = constraintAnnotation.passwordVerificationFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        try {
            return passwordFieldsAreValid(value, context);
        }
        catch (Exception ex) {
            throw new RuntimeException("Exception occurred during validation", ex);
        }
    }

    private boolean passwordFieldsAreValid(Object value, ConstraintValidatorContext context) throws NoSuchFieldException, IllegalAccessException {
        boolean passwordWordFieldsAreValid = true;

        String password = (String) ValidatorUtil.getFieldValue(value, passwordFieldName);
        if (isNullOrEmpty(password)) {
            ValidatorUtil.addValidationError(passwordFieldName, context);
            passwordWordFieldsAreValid = false;
        }

        String passwordVerification = (String) ValidatorUtil.getFieldValue(value, passwordVerificationFieldName);
        if (isNullOrEmpty(passwordVerification)) {
            ValidatorUtil.addValidationError(passwordVerificationFieldName, context);
            passwordWordFieldsAreValid = false;
        }

        return passwordWordFieldsAreValid;
    }

    private boolean isNullOrEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
