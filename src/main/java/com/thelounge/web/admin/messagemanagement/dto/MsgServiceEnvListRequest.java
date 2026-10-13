package com.thelounge.web.admin.messagemanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgServiceEnvListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "company", "regDate", "updateDate");

    private ServiceStatus onService;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
