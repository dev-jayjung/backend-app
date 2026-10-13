package com.thelounge.web.admin.systemmanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorCodeListResponse {
    private Long uid;
    private String critical;
    private String errorCode;
    private String errorMeaningKo;
    private String errorMeaningEn;
    private String errorMeaningCn;
    private String memo;
    private OffsetDateTime regDate;
}
