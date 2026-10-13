package com.thelounge.web.admin.messagemanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsHubStatisticsListResponse {
    private Long uid;
    private String statisticsYearMonth;
    private String userId;
    private String userName;
    private Integer smsCount;
    private Integer lmsCount;
    private Integer mmsCount;
    private String finished;
}
