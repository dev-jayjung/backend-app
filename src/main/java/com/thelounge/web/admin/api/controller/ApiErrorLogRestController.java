package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiErrorLogService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiErrorLogQuery;
import com.thelounge.core.model.api.ApiErrorLogResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiErrorLogResponse;
import com.thelounge.web.admin.api.dto.ApiErrorLogSearchRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-error-logs")
@RequiredArgsConstructor
public class ApiErrorLogRestController {

    private final ApiErrorLogService apiErrorLogService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiErrorLogResponse>>> getList(ApiErrorLogSearchRequest request) {
        final ApiErrorLogQuery query = modelMapperHelper.map(request, ApiErrorLogQuery.class);
        final PageResponse<ApiErrorLogResult> page = apiErrorLogService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiErrorLogResponse.class)));
    }

}
