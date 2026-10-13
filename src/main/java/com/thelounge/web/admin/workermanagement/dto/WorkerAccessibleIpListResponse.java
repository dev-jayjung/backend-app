package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerAccessibleIpListResponse {
    private Long uid;
    private String ipAddress;
    private String editable;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
