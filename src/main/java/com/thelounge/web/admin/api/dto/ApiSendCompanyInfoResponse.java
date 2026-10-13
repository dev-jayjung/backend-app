package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.BaseResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiSendCompanyInfoResponse extends BaseResult {

    private Long uid;

    private String apiAccountId;

    private String companyName;

    private String type;

    private Long loungeCouponIssueHistoryUid;

    private String useSendUrl;

    private String cancelSendUrl;

    private String memo;

}
