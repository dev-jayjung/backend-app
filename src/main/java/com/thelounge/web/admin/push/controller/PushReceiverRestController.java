package com.thelounge.web.admin.push.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.push.PushReceiverService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.push.PushReceiverQuery;
import com.thelounge.core.model.push.PushReceiverResult;
import com.thelounge.core.model.push.PushReceiverMessageQuery;
import com.thelounge.core.model.push.PushReceiverMessageResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/push-receivers")
@RequiredArgsConstructor
public class PushReceiverRestController {

    private final PushReceiverService pushReceiverService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PushReceiverResult>>> getPushReceiverList(
            PushReceiverQuery query) {
        return ResponseUtil.success(pushReceiverService.getPage(query));
    }

    @GetMapping("/message-receivers")
    public ResponseEntity<ApiResponse<PageResponse<PushReceiverMessageResult>>> getReceiverMessageList(
            PushReceiverMessageQuery query) {
        return ResponseUtil.success(pushReceiverService.getReceiverMessagePage(query));
    }
}
