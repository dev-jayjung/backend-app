package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/worker-management/worker-history")
public class WorkerHistoryViewController {

    @GetMapping
    public String workerHistory(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "작업자 계정 변경 이력");

        return "worker-management/worker-history";
    }
}
