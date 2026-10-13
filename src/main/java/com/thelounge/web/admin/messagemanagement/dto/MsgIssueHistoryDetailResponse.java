package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgIssueHistoryDetailResponse {
    private Long uid;
    private String title;
    private String code;
    private Integer sendTarget;
    private String lmsTitle;
    private String msgFormat;
    private Long bbsFileUidMmsImg;
    private String receiverCondition;
    private Integer issueCount;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
