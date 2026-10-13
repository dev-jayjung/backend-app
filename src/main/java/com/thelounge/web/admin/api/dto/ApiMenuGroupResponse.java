package com.thelounge.web.admin.api.dto;

import java.util.List;

import com.thelounge.core.common.dto.BaseResult;
import com.thelounge.core.common.type.ServiceStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiMenuGroupResponse extends BaseResult {
    private Integer uid;

    private String code;

    private String name;

    private String displayOrdinal;

    private ServiceStatus onService;

    private String memo;

    private List<ApiMenuResponse> menuList;
}