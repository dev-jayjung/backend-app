package com.thelounge.web.admin.systemmanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDomainUrlListResponse {
    private Long uid;
    private String name;
    private String serviceType;
    private String hostname;
    private String fullUrl;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
}
