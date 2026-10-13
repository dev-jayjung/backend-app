package com.thelounge.web.admin.messagemanagement.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thelounge.core.admin.message.MsgSenderAllowableService;
import com.thelounge.core.common.type.MsgType;
import com.thelounge.core.common.type.SendStatus;
import com.thelounge.core.common.type.SendTarget;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.message.MsgSenderAllowableQuery;
import com.thelounge.core.model.message.MsgSenderAllowableResult;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-management/msg-history")
public class MsgHistoryViewController {

    private static final int SENDER_OPTION_SIZE = 200;

    private final MsgSenderAllowableService msgSenderAllowableService;

    @GetMapping
    public String msgHistoryList(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "메시지 발송 목록");

        model.addAttribute("sendTargetList", SendTarget.values());
        model.addAttribute("msgTypeList", MsgType.values());
        model.addAttribute("sendStatusList", SendStatus.values());
        model.addAttribute("senderList", senderOptions());

        return "message-management/msg-history";
    }

    private List<MsgSenderAllowableResult> senderOptions() {
        MsgSenderAllowableQuery query = new MsgSenderAllowableQuery();
        query.setSize(SENDER_OPTION_SIZE);
        query.setOnService(ServiceStatus.IN_SERVICE);

        return msgSenderAllowableService.getPage(query).getContent();
    }
}
