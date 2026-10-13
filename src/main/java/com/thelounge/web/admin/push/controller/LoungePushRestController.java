package com.thelounge.web.admin.push.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.push.LoungePushService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.push.LoungePushQuery;
import com.thelounge.core.model.push.LoungePushResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/lounge-pushes")
@RequiredArgsConstructor
public class LoungePushRestController {

    private final LoungePushService loungePushService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<LoungePushResult>>> getList(LoungePushQuery query) {
        return ResponseUtil.success(loungePushService.getPage(query));
    }
}
