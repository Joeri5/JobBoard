package com.github.joeri5.jobboard.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {

    USER,
    ADMIN;

    public GrantedAuthority grantedAuthority() {
        return new SimpleGrantedAuthority(name());
    }

}
