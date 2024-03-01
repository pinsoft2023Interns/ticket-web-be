package com.pinsoft.ticketwebbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN("admin"),
    COMPANY_ADMIN("company_admin"),
    IT_ADMIN("it_admin"),
    COMPANY_USER("company_user")

    ;

    @Getter
    private final String permission;
}
