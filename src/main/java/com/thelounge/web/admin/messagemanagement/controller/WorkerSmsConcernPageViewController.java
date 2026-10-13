package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.worker.WorkerSmsConcernPageService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerSmsConcernPageResult;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsConcernPageDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-management/worker-sms-concern-page")
public class WorkerSmsConcernPageViewController {

    private final WorkerSmsConcernPageService workerSmsConcernPageService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String workerSmsConcernPageList(Model model) {
        addTitle(model);

        return "message-management/worker-sms-concern-page-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("concernPage", new WorkerSmsConcernPageDetailResponse());

        return "message-management/worker-sms-concern-page-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        WorkerSmsConcernPageResult result = workerSmsConcernPageService.get(id);
        model.addAttribute("concernPage",
                modelMapperHelper.map(result, WorkerSmsConcernPageDetailResponse.class));

        return "message-management/worker-sms-concern-page-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "SMS 수신관리");
    }
}
