package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUsageLogSearchRequest extends PageRequest {

    private String accountId;

    private String apiCode;

    private String result;

}
