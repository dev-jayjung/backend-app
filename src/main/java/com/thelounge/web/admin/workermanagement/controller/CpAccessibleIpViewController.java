package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.cp.CpAccessibleIpService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.cp.CpAccessibleIpResult;
import com.thelounge.web.admin.workermanagement.dto.CpAccessibleIpDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worker-management/cp-accessible-ip")
public class CpAccessibleIpViewController {

    private final CpAccessibleIpService cpAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String cpAccessibleIpList(Model model) {
        addTitle(model);

        return "worker-management/cp-accessible-ip-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("accessibleIp", new CpAccessibleIpDetailResponse());

        return "worker-management/cp-accessible-ip-detail";
    }

    /** 수정 폼. */
    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        CpAccessibleIpResult result = cpAccessibleIpService.get(id);
        model.addAttribute("accessibleIp",
                modelMapperHelper.map(result, CpAccessibleIpDetailResponse.class));

        return "worker-management/cp-accessible-ip-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "CP 관리자");
    }
}
