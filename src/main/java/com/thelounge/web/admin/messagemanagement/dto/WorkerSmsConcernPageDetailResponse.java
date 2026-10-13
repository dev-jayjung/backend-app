package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsConcernPageDetailResponse {
    private Long uid;
    private String code;
    private String title;
    private String sourcePosition;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
