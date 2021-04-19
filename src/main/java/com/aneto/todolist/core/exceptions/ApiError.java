package com.aneto.todolist.core.exceptions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiError {

    private final String field;
    private final String errorMessage;

    public ApiError (String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }
}