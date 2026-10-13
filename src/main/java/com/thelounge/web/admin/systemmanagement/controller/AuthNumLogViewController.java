package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-management/auth-num-log")
public class AuthNumLogViewController {

    @GetMapping
    public String authNumLog(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "인증번호 로그");

        return "system-management/auth-num-log";
    }
}
