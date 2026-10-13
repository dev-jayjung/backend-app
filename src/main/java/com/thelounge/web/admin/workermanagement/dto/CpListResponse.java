package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpListResponse {
    private Long uid;
    private String cpGroup;

    /** cpGroup 표시명 (CpServiceGroup) */
    private String cpGroupName;

    private String cpAccountId;
    private String cpName;
    private String supervisor;
    private String userName;
    private String phoneNum;
    private String emailAddress;
    private String allowOnlyOneLogin;
    private Integer serviceStatus;

    /** serviceStatus 표시명 (WorkerServiceStatus) */
    private String serviceStatusName;

    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
