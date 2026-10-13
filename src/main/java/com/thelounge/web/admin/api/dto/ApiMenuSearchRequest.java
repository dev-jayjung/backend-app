package com.thelounge.web.admin.api.dto;

import com.thelounge.core.common.dto.PageRequest;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMenuSearchRequest extends PageRequest {

    private String groupCode;

    private ServiceStatus onService;

}
