package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.message.MsgServiceEnvService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.message.MsgServiceEnvQuery;
import com.thelounge.core.model.message.MsgServiceEnvResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.messagemanagement.dto.MsgServiceEnvListRequest;
import com.thelounge.web.admin.common.util.MaskUtil;
import com.thelounge.web.admin.messagemanagement.dto.MsgServiceEnvListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/msg-service-envs")
@RequiredArgsConstructor
public class MsgServiceEnvRestController {

    private final MsgServiceEnvService msgServiceEnvService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<MsgServiceEnvListResponse>>> getMsgServiceEnvList(
            MsgServiceEnvListRequest request) {
        MsgServiceEnvQuery query = modelMapperHelper.map(request, MsgServiceEnvQuery.class);
        PageResponse<MsgServiceEnvResult> page = msgServiceEnvService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    private MsgServiceEnvListResponse toListResponse(MsgServiceEnvResult result) {
        MsgServiceEnvListResponse response = modelMapperHelper.map(result, MsgServiceEnvListResponse.class);

        response.setUserId(MaskUtil.credential(result.getUserId()));

        return response;
    }
}
