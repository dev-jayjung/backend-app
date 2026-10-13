package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiMemberSearchReqeust extends PageRequest {
    private String serviceCode;

    private String freepassIp;

    private String allowMsgNotify;

    private ServiceStatus onService;

    private String apiMenuCode;
}