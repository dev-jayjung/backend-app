package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.message.MsgSenderAllowableService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.message.MsgSenderAllowableResult;
import com.thelounge.web.admin.messagemanagement.dto.MsgSenderAllowableDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-management/msg-sender-allowable")
public class MsgSenderAllowableViewController {

    private final MsgSenderAllowableService msgSenderAllowableService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String msgSenderAllowableList(Model model) {
        addTitle(model);

        return "message-management/msg-sender-allowable-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("senderAllowable", new MsgSenderAllowableDetailResponse());

        return "message-management/msg-sender-allowable-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        MsgSenderAllowableResult result = msgSenderAllowableService.get(id);
        model.addAttribute("senderAllowable",
                modelMapperHelper.map(result, MsgSenderAllowableDetailResponse.class));

        return "message-management/msg-sender-allowable-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "발신 가능 폰번호");
    }
}
