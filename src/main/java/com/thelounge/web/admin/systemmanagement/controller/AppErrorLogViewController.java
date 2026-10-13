package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thelounge.core.admin.system.AppErrorLogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/system-management/app-error-log")
@RequiredArgsConstructor
public class AppErrorLogViewController {

    private final AppErrorLogService appErrorLogService;

    @GetMapping
    public String appErrorLogList(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "에러로그 목록(앱)");

        model.addAttribute("serviceMediaList", appErrorLogService.getServiceMediaOptions());

        return "system-management/app-error-log";
    }
}
