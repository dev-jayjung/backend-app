package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.AuthNumLogService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.AuthType;
import com.thelounge.core.model.system.AuthNumLogQuery;
import com.thelounge.core.model.system.AuthNumLogResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.AuthNumLogListRequest;
import com.thelounge.web.admin.common.util.MaskUtil;
import com.thelounge.web.admin.systemmanagement.dto.AuthNumLogListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth-num-logs")
@RequiredArgsConstructor
public class AuthNumLogRestController {

    private final AuthNumLogService authNumLogService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AuthNumLogListResponse>>> getAuthNumLogList(
            AuthNumLogListRequest request) {
        AuthNumLogQuery query = modelMapperHelper.map(request, AuthNumLogQuery.class);
        PageResponse<AuthNumLogResult> page = authNumLogService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    private AuthNumLogListResponse toListResponse(AuthNumLogResult result) {
        AuthNumLogListResponse response = modelMapperHelper.map(result, AuthNumLogListResponse.class);

        response.setAuthTypeName(result.getAuthType() != null ? result.getAuthType().getDescription() : "");
        response.setAuthInfo(result.getAuthType() == AuthType.EMAIL ? result.getEmailAddress() : result.getPhoneNum());
        response.setAccountId(MaskUtil.accountId(result.getAccountId()));
        response.setAuthNumComparisonName(
                result.getAuthNumComparison() != null ? result.getAuthNumComparison().getDescription() : "");

        return response;
    }
}
