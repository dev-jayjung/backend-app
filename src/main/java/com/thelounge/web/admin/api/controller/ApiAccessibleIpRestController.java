package com.thelounge.web.admin.api.controller;

import com.thelounge.core.admin.api.ApiAccessibleIpService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiAccessibleIpCommand;
import com.thelounge.core.model.api.ApiAccessibleIpQuery;
import com.thelounge.core.model.api.ApiAccessibleIpResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiAccessibleIpCreateRequest;
import com.thelounge.web.admin.api.dto.ApiAccessibleIpResponse;
import com.thelounge.web.admin.api.dto.ApiAccessibleIpSearchRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * API 접속 허용 IP 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/api-accessible-ips")
@RequiredArgsConstructor
public class ApiAccessibleIpRestController {

    private final ApiAccessibleIpService apiAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;


    /**
     * API 접속 허용 IP 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiAccessibleIpResponse>>> getList(
        ApiAccessibleIpSearchRequest request) {
        final ApiAccessibleIpQuery query = modelMapperHelper.map(request, ApiAccessibleIpQuery.class);
        final PageResponse<ApiAccessibleIpResult> page = apiAccessibleIpService.getPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiAccessibleIpResponse.class)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<ApiAccessibleIpResponse>> create(
        @RequestBody ApiAccessibleIpCreateRequest request) {
        final ApiAccessibleIpCommand command = modelMapperHelper.map(request, ApiAccessibleIpCommand.class);
        apiAccessibleIpService.validate(command, null);
        final ApiAccessibleIpResult menu = apiAccessibleIpService.save(command);
        return ResponseUtil.success(modelMapperHelper.map(menu, ApiAccessibleIpResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id,
        @RequestBody ApiAccessibleIpCreateRequest request) {
        final ApiAccessibleIpCommand command = modelMapperHelper.map(request, ApiAccessibleIpCommand.class);
        apiAccessibleIpService.validate(command, id);
        apiAccessibleIpService.update(id, command);
        return ResponseUtil.success();
    }

}
