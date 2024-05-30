package dev.patika.vetapp.v1.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MODERATOR;

    public String getAuthority() {
        return name();
    }
}
