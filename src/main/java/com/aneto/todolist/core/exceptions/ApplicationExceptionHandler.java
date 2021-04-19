package com.aneto.todolist.core.exceptions;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private final MessageSource validationMessageSource;

    public ApplicationExceptionHandler(@Qualifier("messageSource") MessageSource validationMessageSource) {
        this.validationMessageSource = validationMessageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ApiError> RequestValidation (MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        return fieldErrors.stream().map(fieldError -> {
            String message = validationMessageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new ApiError(fieldError.getField(), message);
        }).collect(Collectors.toList());
    }



}