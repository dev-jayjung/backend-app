package com.thelounge.web.admin.api.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.api.ApiMenuGroupService;
import com.thelounge.core.admin.api.ApiMenuService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.api.ApiMenuGroupQuery;
import com.thelounge.core.model.api.ApiMenuGroupResult;
import com.thelounge.core.model.api.ApiMenuResult;
import com.thelounge.web.admin.api.dto.ApiMenuGroupResponse;
import com.thelounge.web.admin.api.dto.ApiMenuResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api-menus")
@RequiredArgsConstructor
public class ApiMenuViewController {

    private final ApiMenuGroupService apiMenuGroupService;
    private final ApiMenuService apiMenuService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String apiMenuList(Model model) {

        final List<ApiMenuGroupResult> groupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(groupList, ApiMenuGroupResponse.class));

        return "api/menu_list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        final ApiMenuResponse menu = new ApiMenuResponse();
        menu.setOnService(ServiceStatus.IN_SERVICE);
        model.addAttribute("menu", menu);

        final List<ApiMenuGroupResult> groupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(groupList, ApiMenuGroupResponse.class));

        return "api/menu_form";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {

        final ApiMenuResult menu = apiMenuService.get(id);
        model.addAttribute("menu", modelMapperHelper.map(menu, ApiMenuResponse.class));

        final List<ApiMenuGroupResult> groupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(groupList, ApiMenuGroupResponse.class));

        return "api/menu_form";
    }

}
