package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-management/ip2nation-countries")
public class Ip2nationCountriesViewController {

    @GetMapping
    public String ip2nationCountries(Model model) {
        model.addAttribute("mainTitle", "시스템관리(일반)");
        model.addAttribute("subTitle", "IP 국가 정보");

        return "system-management/ip2nation-countries";
    }
}
