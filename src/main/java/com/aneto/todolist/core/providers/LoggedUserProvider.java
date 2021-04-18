package com.aneto.todolist.core.providers;

import com.aneto.todolist.core.security.ApplicationUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggedUserProvider {

    public static String getLoggedUserId() {
        ApplicationUserDetails userDetails = (ApplicationUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userDetails.getUserId();
    }
}
