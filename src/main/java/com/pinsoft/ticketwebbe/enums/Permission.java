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
    COMPANY_ADMIN_UPDATE("company_admin:update"),
    COMPANY_ADMIN_CREATE("company_admin:create"),
    COMPANY_ADMIN_DELETE("company_admin:delete"),
    IT_ADMIN_READ("it_admin:read"),
    IT_ADMIN_UPDATE("it_admin:update"),
    IT_ADMIN_CREATE("it_admin:create"),
    IT_ADMIN_DELETE("it_admin:delete"),
    COMPANY_USER_READ("company_user:read"),
    COMPANY_USER_UPDATE("company_user:update"),
    COMPANY_USER_CREATE("company_user:create"),
    COMPANY_USER_DELETE("company_user:delete"),
    ;

    @Getter
    private final String permission;
}
