package com.pinsoft.ticketwebbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    Permission.ADMIN
            )
    ),
    COMPANY_ADMIN(
            Set.of(
                    Permission.COMPANY_ADMIN
            )
    ),
    COMPANY_USER(
            Set.of(
                    Permission.COMPANY_USER
            )
    ),
    IT_ADMIN(
            Set.of(
                   Permission.IT_ADMIN
            )
    );
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities= getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect((Collectors.toList()));
        authorities.add(new SimpleGrantedAuthority( "ROLE_" +this.name()));
        return authorities;
    }
}
