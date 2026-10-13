package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-management/service-domain-url")
public class ServiceDomainUrlViewController {

    @GetMapping
    public String serviceDomainUrl(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "서비스 도메인 주소");

        return "system-management/service-domain-url";
    }
}
