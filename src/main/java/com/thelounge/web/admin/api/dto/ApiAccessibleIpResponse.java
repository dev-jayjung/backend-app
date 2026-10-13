package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.BaseResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiAccessibleIpResponse extends BaseResult {

    private Long uid;

    private String ipAddress;

    private String memo;

}