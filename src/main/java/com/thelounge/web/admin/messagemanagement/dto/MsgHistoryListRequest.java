package com.thelounge.web.admin.messagemanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.MsgType;
import com.thelounge.core.common.type.SendStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHistoryListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate", "updateDate", "sendDate");

    private Integer sendTarget;

    private Long msgIssueHistoryUid;

    private MsgType msgType;

    private SendStatus sendStatus;

    private String phoneNumReceiverSearch;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
