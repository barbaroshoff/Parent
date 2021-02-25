package com.indomdi.core.service;

import com.indomdi.core.config.GuiRoles;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseServiceImpl {
    protected String message(String message, Object... params) {
        return ReusableMessageFactory.INSTANCE.newMessage(message, params).getFormattedMessage();
    }

    protected String getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    protected boolean isAdmin() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getAuthorities().contains(GuiRoles.ADMIN.authority());
    }
}
