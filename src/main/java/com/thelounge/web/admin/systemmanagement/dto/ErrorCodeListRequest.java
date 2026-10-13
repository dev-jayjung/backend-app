package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorCodeListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "errorCode", "regDate");

    private String critical;

    private Boolean filledKo;

    private Boolean filledEn;

    private Boolean filledCn;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
