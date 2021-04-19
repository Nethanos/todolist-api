package com.aneto.todolist.core.exceptions;

public class ApplicationDomainException extends RuntimeException{

    private String domainEntity;
    public ApplicationDomainException(String message, String domainEntity){
        super(message);
        this.domainEntity = domainEntity;
    }

    public String getDomainEntity() {
        return domainEntity;
    }
}
