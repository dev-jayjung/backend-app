package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.worker.WorkerSmsReceiverService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerSmsReceiverResult;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-management/worker-sms-receiver")
public class WorkerSmsReceiverViewController {

    private final WorkerSmsReceiverService workerSmsReceiverService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String workerSmsReceiverList(Model model) {
        addTitle(model);

        return "message-management/worker-sms-receiver-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("receiver", new WorkerSmsReceiverDetailResponse());

        return "message-management/worker-sms-receiver-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        WorkerSmsReceiverResult result = workerSmsReceiverService.get(id);
        model.addAttribute("receiver", modelMapperHelper.map(result, WorkerSmsReceiverDetailResponse.class));

        return "message-management/worker-sms-receiver-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "SMS 수신자");
    }
}
