package com.thelounge.web.admin.messagemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsHubSendReportListResponse {
    private Long msgId;
    private String scheduleType;
    private String subject;
    private String msg;

    private String sendTime;
    private String deliveryTime;
    private String callback;
    private String calleeNo;
    private String name;
    private Integer result;
    private String resultName;
    private Boolean resultSuccess;

    private String reserved1;
    private String reserved2;
    private String reserved3;
    private String reserved5;

    private String msgKindName;
}
