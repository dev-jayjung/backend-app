package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiUsageLogV3WebhookService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiUsageLogV3WebhookQuery;
import com.thelounge.core.model.api.ApiUsageLogV3WebhookResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiUsageLogV3WebhookResponse;
import com.thelounge.web.admin.api.dto.ApiUsageLogV3WebhookSearchRequest;

import lombok.RequiredArgsConstructor;

/**
 * V3 API 이용 로그(Webhook) 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/api-usage-logs-v3-webhook")
@RequiredArgsConstructor
public class ApiUsageLogV3WebhookRestController {

    private final ApiUsageLogV3WebhookService apiUsageLogV3WebhookService;
    private final ModelMapperHelper modelMapperHelper;


    /**
     * V3 API 이용 로그(Webhook) 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiUsageLogV3WebhookResponse>>> getList(ApiUsageLogV3WebhookSearchRequest request) {
        final ApiUsageLogV3WebhookQuery query = modelMapperHelper.map(request, ApiUsageLogV3WebhookQuery.class);
        final PageResponse<ApiUsageLogV3WebhookResult> page = apiUsageLogV3WebhookService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiUsageLogV3WebhookResponse.class)));
    }

}
