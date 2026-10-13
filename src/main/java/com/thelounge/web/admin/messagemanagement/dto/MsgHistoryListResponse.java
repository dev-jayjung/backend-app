package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHistoryListResponse {
    private Long uid;
    private Integer sendTarget;
    private String sendTargetName;
    private Long msgIssueHistoryUid;
    private String receiverInfo;
    private String receiverInfoDisplay;
    private String phoneNumReceiver;
    private String phoneNumReceiverDisplay;
    private String writerId;
    private String phoneNumSender;
    private String msgTypeName;
    private String lmsTitle;
    private String msg;
    private Long bbsFileUidMmsImg;
    private String sendStatusName;
    private OffsetDateTime sendDate;
    private String msgId;
    private String receiveCode;
    private String receiveMsg;
    private OffsetDateTime receiveDate;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;

    private Boolean sendable;
}
