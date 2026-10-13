package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiSendCompanyDataService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiSendCompanyDataQuery;
import com.thelounge.core.model.api.ApiSendCompanyDataResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiSendCompanyDataResponse;
import com.thelounge.web.admin.api.dto.ApiSendCompanyDataSearchRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-send-company-data")
@RequiredArgsConstructor
public class ApiSendCompanyDataRestController {

    private final ApiSendCompanyDataService apiSendCompanyDataService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiSendCompanyDataResponse>>> getList(ApiSendCompanyDataSearchRequest request) {
        final ApiSendCompanyDataQuery query = modelMapperHelper.map(request, ApiSendCompanyDataQuery.class);
        final PageResponse<ApiSendCompanyDataResult> page = apiSendCompanyDataService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiSendCompanyDataResponse.class)));
    }

}
