package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgServiceEnvListResponse {
    private Long uid;
    private String company;

    private String userId;

    private String defaultCompany;
    private String reportUrl;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
