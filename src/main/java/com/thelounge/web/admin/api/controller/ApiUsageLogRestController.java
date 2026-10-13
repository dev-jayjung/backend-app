package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiUsageLogService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiUsageLogQuery;
import com.thelounge.core.model.api.ApiUsageLogResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiUsageLogResponse;
import com.thelounge.web.admin.api.dto.ApiUsageLogSearchRequest;

import lombok.RequiredArgsConstructor;

/**
 * API 이용 로그 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/api-usage-logs")
@RequiredArgsConstructor
public class ApiUsageLogRestController {

    private final ApiUsageLogService apiUsageLogService;
    private final ModelMapperHelper modelMapperHelper;


    /**
     * API 이용 로그 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiUsageLogResponse>>> getList(ApiUsageLogSearchRequest request) {
        final ApiUsageLogQuery query = modelMapperHelper.map(request, ApiUsageLogQuery.class);
        final PageResponse<ApiUsageLogResult> page = apiUsageLogService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiUsageLogResponse.class)));
    }

}
