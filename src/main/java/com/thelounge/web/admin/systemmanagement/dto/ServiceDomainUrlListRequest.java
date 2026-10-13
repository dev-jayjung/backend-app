package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDomainUrlListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "name", "serviceType", "regDate", "updateDate");

    private ServiceStatus onService;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
