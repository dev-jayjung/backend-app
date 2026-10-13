package com.thelounge.web.admin.systemmanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceEnvListResponse {
    private Long uid;
    private String typeName;
    private String envKey;
    private String envValue;
    private String envJson;
    private String dataType;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;

    private Boolean jsonValue;

    private Boolean editable;
}
