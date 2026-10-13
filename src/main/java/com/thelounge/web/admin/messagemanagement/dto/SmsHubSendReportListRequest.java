package com.thelounge.web.admin.messagemanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsHubSendReportListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("msgId", "sendTime", "deliveryTime", "calleeNo", "result");

    private String reportType;

    private String sendMonth;

    private String tenantId;

    private Integer result;

    private Boolean resultEtc;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "sendTime";
    }
}
