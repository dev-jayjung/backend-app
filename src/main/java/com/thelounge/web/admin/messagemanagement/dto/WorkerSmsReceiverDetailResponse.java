package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsReceiverDetailResponse {
    private Long uid;
    private String accountId;
    private String userName;
    private String phoneNum;
    private String emailAddress;
    private String role;
    private ServiceStatus onService;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
