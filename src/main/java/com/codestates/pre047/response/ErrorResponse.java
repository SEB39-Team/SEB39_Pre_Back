package com.codestates.pre047.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;


import java.util.List;

@Getter
public class ErrorResponse {

    private List<FieldError> fieldErrors;


    public ErrorResponse(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }


    @Getter
    @AllArgsConstructor
    public static class FieldError {

        private String field;
        private Object rejectedValue;
        private String reason;

    }



}
