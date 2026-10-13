package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiErrorCodeService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiErrorCodeQuery;
import com.thelounge.core.model.api.ApiErrorCodeResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiErrorCodeResponse;
import com.thelounge.web.admin.api.dto.ApiErrorCodeSearchRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-error-codes")
@RequiredArgsConstructor
public class ApiErrorCodeRestController {

    private final ApiErrorCodeService apiErrorCodeService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiErrorCodeResponse>>> getList(ApiErrorCodeSearchRequest request) {
        final ApiErrorCodeQuery query = modelMapperHelper.map(request, ApiErrorCodeQuery.class);
        final PageResponse<ApiErrorCodeResult> page = apiErrorCodeService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiErrorCodeResponse.class)));
    }

}
