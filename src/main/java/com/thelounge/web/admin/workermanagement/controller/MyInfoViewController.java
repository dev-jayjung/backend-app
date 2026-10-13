package com.thelounge.web.admin.workermanagement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.thelounge.core.admin.worker.WorkerService;
import com.thelounge.web.admin.common.security.WorkerPrincipal;
@Controller
@RequestMapping("/worker-management/my-info")
public class MyInfoViewController {
    private final WorkerService workerService;
    public MyInfoViewController(WorkerService workerService) { this.workerService = workerService; }
    @GetMapping
    public String myInfo(Model model, @AuthenticationPrincipal WorkerPrincipal principal) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "내정보");
        if (principal != null) model.addAttribute("workerUid", workerService.getByWorkerId(principal.getWorkerId()).getUid());
        return "worker-management/my-info";
    }
}
