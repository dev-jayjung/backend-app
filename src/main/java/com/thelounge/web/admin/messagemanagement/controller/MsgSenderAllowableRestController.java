package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.message.MsgSenderAllowableService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.message.MsgSenderAllowableCommand;
import com.thelounge.core.model.message.MsgSenderAllowableQuery;
import com.thelounge.core.model.message.MsgSenderAllowableResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.messagemanagement.dto.MsgSenderAllowableDetailResponse;
import com.thelounge.web.admin.messagemanagement.dto.MsgSenderAllowableListRequest;
import com.thelounge.web.admin.messagemanagement.dto.MsgSenderAllowableListResponse;
import com.thelounge.web.admin.messagemanagement.dto.MsgSenderAllowableSaveRequest;
import com.thelounge.web.admin.common.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/msg-sender-allowables")
@RequiredArgsConstructor
public class MsgSenderAllowableRestController {

    private final MsgSenderAllowableService msgSenderAllowableService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MsgSenderAllowableListResponse>>> getMsgSenderAllowableList(
            MsgSenderAllowableListRequest request) {
        MsgSenderAllowableQuery query = modelMapperHelper.map(request, MsgSenderAllowableQuery.class);
        PageResponse<MsgSenderAllowableResult> page = msgSenderAllowableService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(MsgSenderAllowableListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MsgSenderAllowableDetailResponse>> create(
            @Valid @RequestBody MsgSenderAllowableSaveRequest request) {
        MsgSenderAllowableCommand command = modelMapperHelper.map(request, MsgSenderAllowableCommand.class);

        MsgSenderAllowableResult result = msgSenderAllowableService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, MsgSenderAllowableDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody MsgSenderAllowableSaveRequest request) {
        MsgSenderAllowableCommand command = modelMapperHelper.map(request, MsgSenderAllowableCommand.class);

        msgSenderAllowableService.update(uid, command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }
}
