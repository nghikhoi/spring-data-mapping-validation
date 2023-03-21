package me.nghikhoi.spring.datamappingvalidation.common.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public class ValidateUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public static void javaxValidate(Object object) throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> violations = VALIDATOR.validate(object);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
