package com.thelounge.web.admin.messagemanagement.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.message.MsgIssueHistoryService;
import com.thelounge.core.admin.message.MsgSenderAllowableService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.SendTarget;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.message.MsgIssueHistoryResult;
import com.thelounge.core.model.message.MsgIssueInputType;
import com.thelounge.core.model.message.MsgSenderAllowableQuery;
import com.thelounge.core.model.message.MsgSenderAllowableResult;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueHistoryDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message-management/msg-issue-history")
public class MsgIssueHistoryViewController {

    private static final int SENDER_OPTION_SIZE = 200;

    private final MsgIssueHistoryService msgIssueHistoryService;
    private final MsgSenderAllowableService msgSenderAllowableService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String msgIssueHistoryList(Model model) {
        addTitle(model);

        model.addAttribute("sendTargetList", SendTarget.values());
        model.addAttribute("inputTypeList", MsgIssueInputType.values());
        model.addAttribute("senderList", senderOptions());

        return "message-management/msg-issue-history-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("issueHistory", new MsgIssueHistoryDetailResponse());
        model.addAttribute("sendTargetList", SendTarget.issueOptions());

        return "message-management/msg-issue-history-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        MsgIssueHistoryResult result = msgIssueHistoryService.get(id);

        model.addAttribute("issueHistory", modelMapperHelper.map(result, MsgIssueHistoryDetailResponse.class));
        model.addAttribute("sendTargetList", editOptions(result.getSendTarget()));

        return "message-management/msg-issue-history-detail";
    }

    private List<SendTarget> editOptions(int sendTarget) {
        return Arrays.stream(SendTarget.values())
                .filter(target -> target.isSelectableOnIssue() || target.getCode() == sendTarget)
                .toList();
    }

    private List<MsgSenderAllowableResult> senderOptions() {
        MsgSenderAllowableQuery query = new MsgSenderAllowableQuery();
        query.setSize(SENDER_OPTION_SIZE);
        query.setOnService(ServiceStatus.IN_SERVICE);

        return msgSenderAllowableService.getPage(query).getContent();
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", "메시지 발행 내역");
    }
}
