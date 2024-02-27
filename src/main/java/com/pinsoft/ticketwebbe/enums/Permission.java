package com.pinsoft.ticketwebbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    COMPANY_ADMIN_READ("company_admin:read"),
    COMPANY_ADMIN_UPDATE("company_admin:update")
    ;

    @Getter
    private final String permission;
}
