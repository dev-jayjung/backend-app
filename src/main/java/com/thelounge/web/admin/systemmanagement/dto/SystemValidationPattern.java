package com.thelounge.web.admin.systemmanagement.dto;

public final class SystemValidationPattern {

    public static final String DOMAIN = "^([A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?|[*])"
            + "([.][A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?)+$";

    public static final String ERROR_CODE = "^[0-9A-Z]+$";

    private SystemValidationPattern() {
    }
}
