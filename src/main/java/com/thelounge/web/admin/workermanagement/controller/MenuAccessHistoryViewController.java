package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker-management/menu-access-history")
public class MenuAccessHistoryViewController {

    @GetMapping
    public String menuAccessHistory(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "관리자 접근 이력");

        return "worker-management/menu-access-history";
    }
}
