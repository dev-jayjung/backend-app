package com.thelounge.web.admin.api.controller;

import com.thelounge.core.admin.api.ApiSendCompanyInfoService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiSendCompanyInfoCommand;
import com.thelounge.core.model.api.ApiSendCompanyInfoQuery;
import com.thelounge.core.model.api.ApiSendCompanyInfoResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiSendCompanyInfoCreateRequest;
import com.thelounge.web.admin.api.dto.ApiSendCompanyInfoResponse;
import com.thelounge.web.admin.api.dto.ApiSendCompanyInfoSearchRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-send-company-infos")
@RequiredArgsConstructor
public class ApiSendCompanyInfoRestController {

    private final ApiSendCompanyInfoService apiSendCompanyInfoService;
    private final ModelMapperHelper modelMapperHelper;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiSendCompanyInfoResponse>>> getList(
        ApiSendCompanyInfoSearchRequest request) {
        final ApiSendCompanyInfoQuery query = modelMapperHelper.map(request, ApiSendCompanyInfoQuery.class);
        final PageResponse<ApiSendCompanyInfoResult> page = apiSendCompanyInfoService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiSendCompanyInfoResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ApiSendCompanyInfoResponse>> create(
        @RequestBody ApiSendCompanyInfoCreateRequest request) {
        final ApiSendCompanyInfoCommand command = modelMapperHelper.map(request, ApiSendCompanyInfoCommand.class);
        final ApiSendCompanyInfoResult result = apiSendCompanyInfoService.save(command);
        return ResponseUtil.success(modelMapperHelper.map(result, ApiSendCompanyInfoResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id,
        @RequestBody ApiSendCompanyInfoCreateRequest request) {
        final ApiSendCompanyInfoCommand command = modelMapperHelper.map(request, ApiSendCompanyInfoCommand.class);
        apiSendCompanyInfoService.update(id, command);
        return ResponseUtil.success();
    }

}
