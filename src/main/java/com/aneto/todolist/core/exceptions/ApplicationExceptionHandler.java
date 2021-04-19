package com.aneto.todolist.core.exceptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource validationMessageSource;

    public ApplicationExceptionHandler(@Qualifier("messageSource") MessageSource validationMessageSource) {
        this.validationMessageSource = validationMessageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    protected List<ApiError> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {

        return ex.getConstraintViolations().stream().map((constraintViolation -> {
            return new ApiError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        })).collect(Collectors.toList());
    }

}