package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker-management/store-login")
public class StoreLoginViewController {

    @GetMapping
    public String storeLogin(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "기업 회원 로그인");

        return "worker-management/store-login";
    }
}
