package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.cp.CpLoginService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.cp.CpLoginQuery;
import com.thelounge.core.model.cp.CpLoginResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.CpLoginListRequest;
import com.thelounge.web.admin.workermanagement.dto.CpLoginListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cp-logins")
@RequiredArgsConstructor
public class CpLoginRestController {
    private final CpLoginService cpLoginService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CpLoginListResponse>>> getCpLoginList(
            CpLoginListRequest request) {
        CpLoginQuery query = modelMapperHelper.map(request, CpLoginQuery.class);
        PageResponse<CpLoginResult> page = cpLoginService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(CpLoginListResponse.class)));
    }
}
