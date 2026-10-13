package com.thelounge.web.admin.push.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/push/push-env")
public class PushEnvViewController {

    @GetMapping
    public String pushEnvList(Model model) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 환경 정보");

        return "push/push-env-list";
    }
}
