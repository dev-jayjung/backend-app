package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiUsageLogV3SearchRequest extends PageRequest {

    private String categoryMajor;

    private String categoryMiddle;

    private String result;

    private String referenceKey;

}
