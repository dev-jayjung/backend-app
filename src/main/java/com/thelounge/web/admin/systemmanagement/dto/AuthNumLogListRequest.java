package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.AuthNumComparison;
import com.thelounge.core.common.type.AuthType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthNumLogListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate");

    private AuthType authType;
    private AuthNumComparison authNumComparison;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
