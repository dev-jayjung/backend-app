package com.thelounge.web.admin.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.api.ApiAccessibleIpService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.api.ApiAccessibleIpResult;
import com.thelounge.web.admin.api.dto.ApiAccessibleIpResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api-accessible-ips")
@RequiredArgsConstructor
public class ApiAccessibleIpViewController {

    private final ApiAccessibleIpService apiAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public String apiAccessibleIpList(Model model) {
        return "api/accessible_ip_list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        final ApiAccessibleIpResponse accessibleIp = new ApiAccessibleIpResponse();
        accessibleIp.setOnService(ServiceStatus.STOPPED);
        model.addAttribute("accessibleIp", accessibleIp);

        return "api/accessible_ip_form";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {

        final ApiAccessibleIpResult menu = apiAccessibleIpService.get(id);
        model.addAttribute("accessibleIp", modelMapperHelper.map(menu, ApiAccessibleIpResponse.class));

        return "api/accessible_ip_form";
    }

}
