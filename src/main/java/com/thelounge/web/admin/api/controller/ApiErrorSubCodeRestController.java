package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiErrorSubCodeService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiErrorSubCodeQuery;
import com.thelounge.core.model.api.ApiErrorSubCodeResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiErrorSubCodeResponse;
import com.thelounge.web.admin.api.dto.ApiErrorSubCodeSearchRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-error-sub-codes")
@RequiredArgsConstructor
public class ApiErrorSubCodeRestController {

    private final ApiErrorSubCodeService apiErrorSubCodeService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiErrorSubCodeResponse>>> getList(ApiErrorSubCodeSearchRequest request) {
        final ApiErrorSubCodeQuery query = modelMapperHelper.map(request, ApiErrorSubCodeQuery.class);
        final PageResponse<ApiErrorSubCodeResult> page = apiErrorSubCodeService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiErrorSubCodeResponse.class)));
    }

}
