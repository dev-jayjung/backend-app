package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ConfirmStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppErrorLogListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "appVersion", "deviceSn", "accountId", "countryCode", "regDate");

    private String serviceMedia;

    private ConfirmStatus confirmStatus;

    private Boolean countryKr;

    private Boolean appSafer;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
