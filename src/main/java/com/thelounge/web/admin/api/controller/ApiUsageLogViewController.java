package com.thelounge.web.admin.api.controller;

import com.thelounge.core.admin.api.ApiMemberService;
import com.thelounge.core.admin.api.ApiMenuService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.api.ApiMemberResult;
import com.thelounge.core.model.api.ApiMenuQuery;
import com.thelounge.core.model.api.ApiMenuResult;
import com.thelounge.web.admin.api.dto.ApiMemberResponse;
import com.thelounge.web.admin.api.dto.ApiMenuResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api-usage-logs")
@RequiredArgsConstructor
public class ApiUsageLogViewController {

    private final ApiMemberService apiMemberService;
    private final ApiMenuService apiMenuService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public String apiUsageLogList(Model model) {

        final List<ApiMemberResult> memberList = apiMemberService.getInServiceList();
        model.addAttribute("apiMemberList", modelMapperHelper.mapList(memberList, ApiMemberResponse.class));

        final ApiMenuQuery menuQuery = new ApiMenuQuery();
        menuQuery.setOnService(ServiceStatus.IN_SERVICE);
        final List<ApiMenuResult> menuList = apiMenuService.getList(menuQuery);
        model.addAttribute("apiMenuList", modelMapperHelper.mapList(menuList, ApiMenuResponse.class));

        return "api/usage_log_list";
    }

}
