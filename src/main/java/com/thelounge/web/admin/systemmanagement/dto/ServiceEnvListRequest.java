package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceEnvListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate", "updateDate");

    private String type;

    public ServiceEnvListRequest() {
        setDirection("ASC");
    }

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
