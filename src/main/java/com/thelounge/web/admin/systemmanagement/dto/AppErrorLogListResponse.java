package com.thelounge.web.admin.systemmanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ConfirmStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppErrorLogListResponse {
    private Long uid;
    private String serviceMediaName;
    private String appVersion;
    private String deviceSn;
    private String accountId;
    private String countryCode;
    private String errorCode;
    private String errorMsg;
    private String networkStatus;
    private String osVersion;
    private String modelName;
    private String etc;
    private String ipAddress;
    private ConfirmStatus confirmStatus;
    private OffsetDateTime regDate;
}
