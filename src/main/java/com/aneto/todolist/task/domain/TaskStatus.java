package com.aneto.todolist.task.domain;

import java.util.Locale;

public enum TaskStatus {

    PENDING, COMPLETED;

    public static boolean isValid(String status){
        return status.toUpperCase() == PENDING.toString() ||
                status.toUpperCase() == COMPLETED.toString();
    }
}
