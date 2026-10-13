package com.thelounge.web.admin.messagemanagement.controller;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.message.MsgHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.SendStatus;
import com.thelounge.core.common.type.SendTarget;
import com.thelounge.core.model.message.MsgHistoryCommand;
import com.thelounge.core.model.message.MsgHistoryQuery;
import com.thelounge.core.model.message.MsgHistoryResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.MaskUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.messagemanagement.dto.MsgHistoryBulkRequest;
import com.thelounge.web.admin.messagemanagement.dto.MsgHistoryListRequest;
import com.thelounge.web.admin.messagemanagement.dto.MsgHistoryListResponse;
import com.thelounge.web.admin.messagemanagement.dto.MsgHistorySendRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/msg-histories")
@RequiredArgsConstructor
public class MsgHistoryRestController {

    private static final String OWNER_ID_WORK = "";
    private static final DateTimeFormatter EXPORT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final MsgHistoryService msgHistoryService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MsgHistoryListResponse>>> getMsgHistoryList(
            MsgHistoryListRequest request) {
        PageResponse<MsgHistoryResult> page = msgHistoryService.getPage(toQuery(request));

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Long>> register(@Valid @RequestBody MsgHistorySendRequest request) {
        MsgHistoryCommand command = modelMapperHelper.map(request, MsgHistoryCommand.class);
        command.setOwnerId(OWNER_ID_WORK);
        command.setWriterId(SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success(msgHistoryService.register(command).getUid());
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Integer>> deleteAll(@Valid @RequestBody MsgHistoryBulkRequest request) {
        return ResponseUtil.success(msgHistoryService.deleteAll(request.getUids()));
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> export(MsgHistoryListRequest request) {
        List<MsgHistoryResult> rows = msgHistoryService.getAllForExport(toQuery(request));

        StringBuilder csv = new StringBuilder("﻿");
        csv.append("코드,발송 유형,단체 발송 번호,수신자,수신자 폰번호,등록자,발신 폰번호,메시지 유형,메시지,발송 결과,발송일,등록일\n");

        rows.forEach(row -> csv.append(toCsvLine(row)));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"msg-history.csv\"; filename*=UTF-8''msg-history.csv")
                .contentType(new MediaType("text", "csv", StandardCharsets.UTF_8))
                .body(csv.toString().getBytes(StandardCharsets.UTF_8));
    }

    private MsgHistoryQuery toQuery(MsgHistoryListRequest request) {
        MsgHistoryQuery query = modelMapperHelper.map(request, MsgHistoryQuery.class);
        query.setOwnerId(OWNER_ID_WORK);

        return query;
    }

    private MsgHistoryListResponse toListResponse(MsgHistoryResult result) {
        MsgHistoryListResponse response = modelMapperHelper.map(result, MsgHistoryListResponse.class);

        response.setSendTargetName(SendTarget.descriptionOf(result.getSendTarget()));
        response.setMsgTypeName(result.getMsgType() != null ? result.getMsgType().getDescription() : "");
        response.setSendStatusName(result.getSendStatus() != null ? result.getSendStatus().getDescription() : "");
        response.setReceiverInfoDisplay(MaskUtil.userName(result.getReceiverInfo()));
        response.setPhoneNumReceiverDisplay(MaskUtil.phoneNum(result.getPhoneNumReceiver()));
        response.setWriterId(MaskUtil.accountId(result.getWriterId()));
        response.setSendable(result.getSendStatus() == SendStatus.UNKNOWN);

        return response;
    }

    private String toCsvLine(MsgHistoryResult row) {
        return String.join(",",
                String.valueOf(row.getUid()),
                csv(SendTarget.descriptionOf(row.getSendTarget())),
                String.valueOf(row.getMsgIssueHistoryUid()),
                csv(row.getReceiverInfo()),
                csv(row.getPhoneNumReceiver()),
                csv(row.getWriterId()),
                csv(row.getPhoneNumSender()),
                csv(row.getMsgType() != null ? row.getMsgType().getDescription() : ""),
                csv(row.getMsg()),
                csv(row.getSendStatus() != null ? row.getSendStatus().getDescription() : ""),
                csv(row.getSendDate() != null ? EXPORT_DATE_FORMAT.format(row.getSendDate()) : ""),
                csv(EXPORT_DATE_FORMAT.format(row.getRegDate()))) + "\n";
    }

    private static String csv(String value) {
        return "\"" + nullToEmpty(value).replace("\"", "\"\"") + "\"";
    }

    private static String nullToEmpty(String value) {
        return value != null ? value : "";
    }
}
