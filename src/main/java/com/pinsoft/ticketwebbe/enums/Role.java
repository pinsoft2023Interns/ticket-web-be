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
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE
            )
    ),
    COMPANY_ADMIN(
            Set.of(
                    Permission.COMPANY_ADMIN_READ,
                    Permission.COMPANY_ADMIN_UPDATE
            )
    ),
    COMPANY_USER(Collections.emptySet()),
    IT_ADMIN(
            Set.of(
                    Permission.ADMIN_CREATE
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
