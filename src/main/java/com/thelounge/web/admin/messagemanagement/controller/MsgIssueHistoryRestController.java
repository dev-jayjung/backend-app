package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.message.MsgIssueHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.SendTarget;
import com.thelounge.core.model.message.MsgIssueCommand;
import com.thelounge.core.model.message.MsgIssueHistoryCommand;
import com.thelounge.core.model.message.MsgIssueHistoryQuery;
import com.thelounge.core.model.message.MsgIssueHistoryResult;
import com.thelounge.core.model.message.MsgIssueResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.MaskUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueHistoryDetailResponse;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueHistoryListRequest;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueHistoryListResponse;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueHistorySaveRequest;
import com.thelounge.web.admin.messagemanagement.dto.MsgIssueRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/msg-issue-histories")
@RequiredArgsConstructor
public class MsgIssueHistoryRestController {

    private static final String OWNER_ID_WORK = "";

    private final MsgIssueHistoryService msgIssueHistoryService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MsgIssueHistoryListResponse>>> getMsgIssueHistoryList(
            MsgIssueHistoryListRequest request) {
        MsgIssueHistoryQuery query = modelMapperHelper.map(request, MsgIssueHistoryQuery.class);
        query.setOwnerId(OWNER_ID_WORK);

        PageResponse<MsgIssueHistoryResult> page = msgIssueHistoryService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MsgIssueHistoryDetailResponse>> create(
            @Valid @RequestBody MsgIssueHistorySaveRequest request) {
        MsgIssueHistoryCommand command = toCommand(request);

        MsgIssueHistoryResult result = msgIssueHistoryService.save(command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success(modelMapperHelper.map(result, MsgIssueHistoryDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody MsgIssueHistorySaveRequest request) {
        msgIssueHistoryService.update(uid, toCommand(request), SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }

    @PostMapping("/{uid}/issue")
    public ResponseEntity<ApiResponse<MsgIssueResult>> issue(@PathVariable Long uid,
            @Valid @RequestBody MsgIssueRequest request) {
        MsgIssueCommand command = modelMapperHelper.map(request, MsgIssueCommand.class);
        command.setWriterId(SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success(msgIssueHistoryService.issue(uid, command));
    }

    private MsgIssueHistoryCommand toCommand(MsgIssueHistorySaveRequest request) {
        MsgIssueHistoryCommand command = modelMapperHelper.map(request, MsgIssueHistoryCommand.class);
        command.setOwnerId(OWNER_ID_WORK);

        return command;
    }

    private MsgIssueHistoryListResponse toListResponse(MsgIssueHistoryResult result) {
        MsgIssueHistoryListResponse response = modelMapperHelper.map(result, MsgIssueHistoryListResponse.class);

        response.setSendTargetName(SendTarget.descriptionOf(result.getSendTarget()));
        response.setWriterId(MaskUtil.accountId(result.getWriterId()));

        return response;
    }
}
