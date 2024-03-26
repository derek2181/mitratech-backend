package com.talentreef.interviewquestions.takehome.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.*;

public class RequestValidator {
    private Validator validator;

    public RequestValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public Map<String, List<String>> validate(Object request) {
        Set<ConstraintViolation<Object>> violations = validator.validate(request);
        Map<String, List<String>> errorMessages = new HashMap<>();
        for (ConstraintViolation<Object> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            errorMessages.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(violation.getMessage());
        }
        return errorMessages;
    }
}
