package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.system.CrossDomainAllowableService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.CrossDomainAllowableResult;
import com.thelounge.web.admin.systemmanagement.dto.CrossDomainAllowableDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/system-management/cross-domain-allowable")
public class CrossDomainAllowableViewController {

    private final CrossDomainAllowableService crossDomainAllowableService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String crossDomainAllowableList(Model model) {
        addTitle(model);

        return "system-management/cross-domain-allowable-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("crossDomain", new CrossDomainAllowableDetailResponse());

        return "system-management/cross-domain-allowable-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        CrossDomainAllowableResult result = crossDomainAllowableService.get(id);
        model.addAttribute("crossDomain",
                modelMapperHelper.map(result, CrossDomainAllowableDetailResponse.class));

        return "system-management/cross-domain-allowable-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "허용 크로스 도메인");
    }
}
