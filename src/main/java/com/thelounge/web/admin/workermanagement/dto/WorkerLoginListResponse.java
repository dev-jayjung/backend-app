package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerLoginListResponse {
    private Long uid;
    private String workerId;
    private String workerName;
    private String loginSuccess;
    private OffsetDateTime loginDate;
    private OffsetDateTime logoutDate;
    private String sessionId;
    private String deviceSn;
    private String ipAddress;
    private OffsetDateTime regDate;
}
