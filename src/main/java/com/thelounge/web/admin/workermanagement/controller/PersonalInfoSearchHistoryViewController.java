package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker-management/personal-info-search-history")
public class PersonalInfoSearchHistoryViewController {

    @GetMapping
    public String personalInfoSearchHistory(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "개인정보 조회 이력");

        return "worker-management/personal-info-search-history";
    }
}
