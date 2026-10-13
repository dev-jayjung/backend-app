package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpLoginListResponse {
    private Long uid;
    private String workerId;
    private String cpName;
    private String loginSuccess;
    private OffsetDateTime loginDate;
    private OffsetDateTime logoutDate;
    private String ipAddress;
    private OffsetDateTime regDate;
}
