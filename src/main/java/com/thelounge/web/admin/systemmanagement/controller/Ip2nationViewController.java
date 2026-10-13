package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-management/ip2nation")
public class Ip2nationViewController {

    @GetMapping
    public String ip2nation(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "나라별 IP대역");

        return "system-management/ip2nation";
    }
}
