package com.indomdi.core.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public enum GuiRoles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private SimpleGrantedAuthority authority;
    private String role;

    private GuiRoles(String role) {
        this.role = role;
        authority = new SimpleGrantedAuthority(role);
    }

    public SimpleGrantedAuthority authority() {
        return authority;
    }

    public String role() {
        return toString();
    }

    public static GuiRoles fromString(String role) {
        return Arrays.asList(GuiRoles.values()).stream().filter(v -> v.role.equals(role)).findFirst().orElse(null);
    }
}
