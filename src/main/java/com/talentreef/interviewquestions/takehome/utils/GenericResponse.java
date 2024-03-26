package com.talentreef.interviewquestions.takehome.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GenericResponse<T> {
    public Integer code;
    public String message;
    public T result;
    public Map<String, List<String>> errors;
}