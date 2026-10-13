package com.thelounge.web.admin.systemmanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthNumLogListResponse {
    private Long uid;

    private String authTypeName;

    private String authInfo;

    private String authNum;
    private String authSessionId;

    private String accountId;

    private String roleCode;
    private String userData;
    private String authNumComparisonName;
    private OffsetDateTime regDate;
}
