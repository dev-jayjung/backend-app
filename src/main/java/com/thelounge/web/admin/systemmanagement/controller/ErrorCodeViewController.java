package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.system.ErrorCodeService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.ErrorCodeResult;
import com.thelounge.web.admin.systemmanagement.dto.ErrorCodeDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/system-management/error-code")
public class ErrorCodeViewController {

    private final ErrorCodeService errorCodeService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String errorCodeList(Model model) {
        addTitle(model);

        return "system-management/error-code-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("errorCode", new ErrorCodeDetailResponse());

        return "system-management/error-code-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        ErrorCodeResult result = errorCodeService.get(id);
        model.addAttribute("errorCode", modelMapperHelper.map(result, ErrorCodeDetailResponse.class));

        return "system-management/error-code-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "에러코드 목록");
    }
}
