package com.thelounge.web.admin.push.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.push.PushReceiverService;
import com.thelounge.core.model.push.PushServiceMedia;

@Controller
@RequestMapping("/push/push-receiver")
@RequiredArgsConstructor
public class PushReceiverViewController {

    private final PushReceiverService pushReceiverService;

    @GetMapping
    public String pushReceiverList(@RequestParam(required = false) Integer messageUid, Model model) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 수신자 목록");
        model.addAttribute("pushServiceMedias", PushServiceMedia.values());
        model.addAttribute("messageUid", messageUid);

        return "push/push-receiver-list";
    }

    @GetMapping("/receive-detail")
    public String receiveDetail(@RequestParam String deviceSn, Model model) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 수신 이력");
        model.addAttribute("receiver", pushReceiverService.getByDeviceSn(deviceSn));
        model.addAttribute("sendTargets", com.thelounge.web.admin.push.type.PushSendTarget.values());
        return "push/push-receiver-receive-detail";
    }
}
