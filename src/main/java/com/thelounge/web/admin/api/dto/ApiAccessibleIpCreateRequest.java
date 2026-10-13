package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiAccessibleIpCreateRequest {

    private String ipAddress;

    private ServiceStatus onService;

    private String memo;

}
