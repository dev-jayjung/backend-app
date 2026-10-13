package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.AppErrorLogService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.ServiceMedia;
import com.thelounge.core.model.system.AppErrorLogQuery;
import com.thelounge.core.model.system.AppErrorLogResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.AppErrorLogBulkRequest;
import com.thelounge.web.admin.systemmanagement.dto.AppErrorLogListRequest;
import com.thelounge.web.admin.systemmanagement.dto.AppErrorLogListResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/app-error-logs")
@RequiredArgsConstructor
public class AppErrorLogRestController {

    private final AppErrorLogService appErrorLogService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AppErrorLogListResponse>>> getAppErrorLogList(
            AppErrorLogListRequest request) {
        AppErrorLogQuery query = modelMapperHelper.map(request, AppErrorLogQuery.class);
        PageResponse<AppErrorLogResult> page = appErrorLogService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    private AppErrorLogListResponse toListResponse(AppErrorLogResult result) {
        AppErrorLogListResponse response = modelMapperHelper.map(result, AppErrorLogListResponse.class);

        ServiceMedia serviceMedia = ServiceMedia.of(result.getServiceMedia());
        response.setServiceMediaName(serviceMedia != null ? serviceMedia.getDescription() : "");

        return response;
    }

    @PutMapping("/confirm-status")
    public ResponseEntity<ApiResponse<Integer>> toggleConfirmStatus(@Valid @RequestBody AppErrorLogBulkRequest request) {
        return ResponseUtil.success(appErrorLogService.toggleConfirmStatus(request.getUids()));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAll(@Valid @RequestBody AppErrorLogBulkRequest request) {
        appErrorLogService.deleteAll(request.getUids());

        return ResponseUtil.success();
    }
}
