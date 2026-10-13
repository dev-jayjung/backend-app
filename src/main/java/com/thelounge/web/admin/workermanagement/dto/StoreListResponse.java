package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreListResponse {
    private Long uid;
    private String storeGroup;
    private String storeAccountId;
    private String storeName;
    private String discountCode;
    private String corporateRegistrationNumber;
    private String businessCode;
    private Integer serviceStatus;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
