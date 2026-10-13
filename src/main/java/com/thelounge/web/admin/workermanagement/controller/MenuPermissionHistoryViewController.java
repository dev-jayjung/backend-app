package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker-management/menu-permission-history")
public class MenuPermissionHistoryViewController {

    @GetMapping
    public String menuPermissionHistory(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "관리자 메뉴 권한 변경 이력");

        return "worker-management/menu-permission-history";
    }
}
