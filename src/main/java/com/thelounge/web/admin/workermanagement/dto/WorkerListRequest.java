package com.thelounge.web.admin.workermanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkerListRequest extends PageRequest {

    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate", "updateDate");

    private String menuAuth;
    private String serviceOperator;
    private String allowOnlyOneLogin;
    private ServiceStatus onService;

    @Override
    public String getSortBy() {
        return SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
