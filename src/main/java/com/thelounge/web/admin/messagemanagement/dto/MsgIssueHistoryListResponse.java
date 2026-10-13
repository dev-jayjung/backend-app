package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgIssueHistoryListResponse {
    private Long uid;
    private String title;
    private String code;
    private Integer sendTarget;
    private String sendTargetName;
    private String lmsTitle;
    private String msgFormat;
    private Long bbsFileUidMmsImg;
    private String receiverCondition;
    private Integer issueCount;
    private String memo;
    private String writerId;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
