package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.BaseResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMenuSearchResponse extends BaseResult {

    private Long uid;

    private String groupCode;

    private String code;

    private String name;

    private Integer displayOrdinal;

    private String linkUrl;

    private String memo;

    private String groupName;

}
