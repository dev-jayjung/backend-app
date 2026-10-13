package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerDetailResponse {
    private Integer uid;
    private String workerId;
    private String workerName;
    private String phoneNum;
    private String emailAddress;
    private String defaultMenuCode;
    private String menuAuth;
    private String serviceOperator;
    private Integer workerMaxIssueCount;
    private String saleChannel;
    private String autoLogin;
    private String allowOnlyOneLogin;
    private String memo;
    private ServiceStatus onService;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
