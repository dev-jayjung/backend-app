package com.thelounge.web.admin.messagemanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsReceiverListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "accountId", "userName", "regDate", "updateDate");

    private ServiceStatus onService;

    private String phoneNumSearch;

    private Long concernPageUid;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
