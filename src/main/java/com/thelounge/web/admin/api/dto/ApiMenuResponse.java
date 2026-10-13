package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.BaseResult;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiMenuResponse extends BaseResult {
    private Long uid;

    private String groupCode;

    private String code;

    private String name;

    private Integer displayOrdinal;

    private ServiceStatus onService;

    private String linkUrl;

    private String memo;
}
