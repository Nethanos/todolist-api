package com.aneto.todolist.core.exceptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource validationMessageSource;

    public ApplicationExceptionHandler(@Qualifier("messageSource") MessageSource validationMessageSource) {
        this.validationMessageSource = validationMessageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected List<ApiError> handleConstraintViolation(
            javax.validation.ConstraintViolationException ex) {

        return ex.getConstraintViolations().stream().map((constraintViolation -> {
            return new ApiError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        })).collect(Collectors.toList());
    }


    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationDomainException.class)
    protected ApiError ApplicationDomainException(ApplicationDomainException ex){
        return new ApiError(ex.getDomainEntity(), ex.getMessage());
    }
}