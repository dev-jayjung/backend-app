package com.thelounge.web.admin.push.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.web.admin.push.type.PushSendTarget;
import com.thelounge.core.admin.push.PushMessageService;
import com.thelounge.core.model.push.PushMessageResult;
import com.thelounge.core.model.push.PushMessageSendStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/push/push-message")
@RequiredArgsConstructor
public class PushMessageViewController {

    private final PushMessageService pushMessageService;

    @GetMapping
    public String pushMessageList(Model model) {
        Map<String, String> searchTargets = new LinkedHashMap<>();
        searchTargets.put("uid", "코드");
        searchTargets.put("code", "구분 코드");
        searchTargets.put("admin_title", "관리 제목");
        searchTargets.put("writer_id", "작성자 ID");

        Map<Integer, String> sendTargetLabels = new LinkedHashMap<>();
        for (PushSendTarget target : PushSendTarget.values()) {
            sendTargetLabels.put(target.getValue(), target.getDescription());
        }
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 메시지 목록");
        model.addAttribute("searchTargets", searchTargets);
        model.addAttribute("sendTargets", PushSendTarget.values());
        model.addAttribute("sendTargetLabels", sendTargetLabels);

        return "push/push-message-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        final String defaultDisplayMessageBox = "1";
        final String defaultDisplayClose = "1";
        final String defaultCloseButtonName = "닫기";
        final String defaultViewButtonName = "보기";
        final String defaultReloadOnResume = "y";
        final String defaultMotionClose = "n";
        final String defaultBounceEffect = "y";

        PushMessageResult detail = new PushMessageResult();
        detail.setSendTarget(PushSendTarget.ALL_NOTICE.getValue());
        detail.setDisplayMessageBox(defaultDisplayMessageBox);
        detail.setDisplayClose(defaultDisplayClose);
        detail.setCloseBtnName(defaultCloseButtonName);
        detail.setViewBtnName(defaultViewButtonName);
        detail.setReloadOnResume(defaultReloadOnResume);
        detail.setMotionClose(defaultMotionClose);
        detail.setBounceEffect(defaultBounceEffect);
        addDetailModel(model, detail);
        return "push/push-message-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Integer id, Model model) {
        addDetailModel(model, pushMessageService.get(id));
        return "push/push-message-detail";
    }

    @GetMapping("/info")
    public String info(@RequestParam Integer id, Model model) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 메시지 정보");
        model.addAttribute("message", pushMessageService.get(id));
        Map<Integer, String> sendTargetLabels = new LinkedHashMap<>();
        for (PushSendTarget target : PushSendTarget.values()) {
            sendTargetLabels.put(target.getValue(), target.getDescription());
        }
        model.addAttribute("sendTargetLabels", sendTargetLabels);
        Map<String, String> sendStatusLabels = new LinkedHashMap<>();
        for (PushMessageSendStatus status : PushMessageSendStatus.values()) {
            sendStatusLabels.put(status.getCode(), status.getDescription());
        }
        model.addAttribute("pushServiceMedias", pushMessageService.getActiveServiceMedias());
        model.addAttribute("pushSendStatuses", PushMessageSendStatus.values());
        model.addAttribute("sendStatusLabels", sendStatusLabels);
        return "push/push-message-info";
    }

    @GetMapping("/receivers/group")
    public String groupReceiverSelect(@RequestParam Integer messageUid, Model model) {
        pushMessageService.get(messageUid);
        model.addAttribute("messageUid", messageUid);
        return "push/push-message-receiver-group-select";
    }

    private void addDetailModel(Model model, PushMessageResult result) {
        Map<Integer, String> sendTargets = new LinkedHashMap<>();
        for (PushSendTarget target : PushSendTarget.values()) {
            sendTargets.put(target.getValue(), target.getDescription());
        }
        var left = pushMessageService.getNavigationElements("btn_left");
        var right = pushMessageService.getNavigationElements("btn_right");
        var templates = pushMessageService.getNavigationElements("template");
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", result.getUid() == null ? "푸시 메시지 등록" : "푸시 메시지 상세");
        model.addAttribute("message", result);
        model.addAttribute("sendTargets", sendTargets);
        model.addAttribute("leftButtons", left);
        model.addAttribute("rightButtons", right);
        model.addAttribute("templates", templates);
    }
}
