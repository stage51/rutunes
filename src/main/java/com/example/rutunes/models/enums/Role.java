package com.example.rutunes.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_ARTIST;

    @Override
    public String getAuthority() {
        return name();
    }
}
