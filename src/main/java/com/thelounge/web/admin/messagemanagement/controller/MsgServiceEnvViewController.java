package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message-management/msg-service-env")
public class MsgServiceEnvViewController {

    @GetMapping
    public String msgServiceEnv(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "메시지 환경 정보");

        return "message-management/msg-service-env";
    }
}
