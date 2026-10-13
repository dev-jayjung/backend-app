package com.thelounge.web.admin.push.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.push.PushEnvService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.push.PushEnvQuery;
import com.thelounge.core.model.push.PushEnvResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/push-envs")
@RequiredArgsConstructor
public class PushEnvRestController {

    private final PushEnvService pushEnvService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PushEnvResult>>> getPushEnvList(PushEnvQuery query) {
        return ResponseUtil.success(pushEnvService.getPage(query));
    }
}
