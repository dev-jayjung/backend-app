package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.ServiceEnvService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.ServiceEnvQuery;
import com.thelounge.core.model.system.ServiceEnvResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.systemmanagement.dto.ServiceEnvListRequest;
import com.thelounge.web.admin.systemmanagement.dto.ServiceEnvListResponse;
import com.thelounge.web.admin.systemmanagement.dto.ServiceEnvValueRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/service-envs")
@RequiredArgsConstructor
public class ServiceEnvRestController {

    private static final String TYPE_SYSTEM = "S";
    private static final String TYPE_USER = "U";

    private final ServiceEnvService serviceEnvService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ServiceEnvListResponse>>> getServiceEnvList(
            ServiceEnvListRequest request) {
        ServiceEnvQuery query = modelMapperHelper.map(request, ServiceEnvQuery.class);
        PageResponse<ServiceEnvResult> page = serviceEnvService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    @PutMapping("/{uid}/value")
    public ResponseEntity<ApiResponse<Void>> updateValue(@PathVariable Long uid,
            @RequestBody ServiceEnvValueRequest request) {
        serviceEnvService.updateInline(uid, request.getField(), request.getValue(),
                SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }

    private ServiceEnvListResponse toListResponse(ServiceEnvResult result) {
        ServiceEnvListResponse response = modelMapperHelper.map(result, ServiceEnvListResponse.class);

        response.setTypeName(typeName(result.getType()));
        response.setJsonValue(false);
        response.setEditable(TYPE_USER.equals(result.getType()));

        return response;
    }

    private String typeName(String type) {
        return switch (type) {
            case TYPE_SYSTEM -> "시스템";
            case TYPE_USER -> "사용자";
            default -> "";
        };
    }

}
