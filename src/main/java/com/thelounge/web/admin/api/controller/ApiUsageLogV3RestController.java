package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiUsageLogV3Service;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiUsageLogV3Query;
import com.thelounge.core.model.api.ApiUsageLogV3Result;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiUsageLogV3Response;
import com.thelounge.web.admin.api.dto.ApiUsageLogV3SearchRequest;

import lombok.RequiredArgsConstructor;

/**
 * V3 API 이용 로그 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/api-usage-logs-v3")
@RequiredArgsConstructor
public class ApiUsageLogV3RestController {

    private final ApiUsageLogV3Service apiUsageLogV3Service;
    private final ModelMapperHelper modelMapperHelper;


    /**
     * V3 API 이용 로그 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiUsageLogV3Response>>> getList(ApiUsageLogV3SearchRequest request) {
        final ApiUsageLogV3Query query = modelMapperHelper.map(request, ApiUsageLogV3Query.class);
        final PageResponse<ApiUsageLogV3Result> page = apiUsageLogV3Service.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiUsageLogV3Response.class)));
    }

}
