package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgSenderAllowableDetailResponse {
    private Long uid;
    private String company;
    private String phoneNum;
    private String callbackCtn;
    private String userName;
    private String defaultSender;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
