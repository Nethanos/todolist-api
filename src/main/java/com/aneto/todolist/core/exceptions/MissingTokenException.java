package com.aneto.todolist.core.exceptions;

public class MissingTokenException extends RuntimeException{

    public MissingTokenException(String message) {
        super(message);
    }
}
