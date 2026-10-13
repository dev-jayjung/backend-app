package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiUsageLogDpTempService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiUsageLogDpTempQuery;
import com.thelounge.core.model.api.ApiUsageLogDpTempResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiUsageLogDpTempResponse;
import com.thelounge.web.admin.api.dto.ApiUsageLogDpTempSearchRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-usage-log-dp-temps")
@RequiredArgsConstructor
public class ApiUsageLogDpTempRestController {

    private final ApiUsageLogDpTempService apiUsageLogDpTempService;
    private final ModelMapperHelper modelMapperHelper;


    /**
     * API 메뉴 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiUsageLogDpTempResponse>>> getList(ApiUsageLogDpTempSearchRequest request) {
        final ApiUsageLogDpTempQuery query = modelMapperHelper.map(request, ApiUsageLogDpTempQuery.class);
        final PageResponse<ApiUsageLogDpTempResult> page = apiUsageLogDpTempService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiUsageLogDpTempResponse.class)));
    }

    /**
     * DP API 이용 로그 동기화 (api_usage_log -> api_usage_log_dp_temp)
     *
     * @return 동기화된 건수
     */
    @PostMapping("/sync")
    public ResponseEntity<ApiResponse<Integer>> sync() {
        final int syncedCount = apiUsageLogDpTempService.sync();
        return ResponseUtil.success(syncedCount);
    }

}
