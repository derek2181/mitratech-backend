package com.talentreef.interviewquestions.takehome.utils;

import jakarta.validation.ConstraintViolation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationMessages {
    public static <T> GenericResponse getValidationMessages(Set<ConstraintViolation<T>> violations){
        GenericResponse<List<String>> response = new GenericResponse<>();
        if(!violations.isEmpty()){
            List<String> errorMessages = violations.stream()
                    .map(violation -> "Error in the field "+violation.getPropertyPath() + ": " + violation.getMessage())
                    .collect(Collectors.toList());

            response.setResult(errorMessages);
            response.setCode(400);
            response.setMessage("The request is not properly structured");
            return response;
        }

        return null;
    }

}
