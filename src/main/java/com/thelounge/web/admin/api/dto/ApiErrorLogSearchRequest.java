package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorLogSearchRequest extends PageRequest {

    private String accountId;

    private String apiCode;

}
