package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerAccessibleIpService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerAccessibleIpCommand;
import com.thelounge.core.model.worker.WorkerAccessibleIpQuery;
import com.thelounge.core.model.worker.WorkerAccessibleIpResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpListRequest;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpListResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-accessible-ips")
@RequiredArgsConstructor
public class WorkerAccessibleIpRestController {
    private final WorkerAccessibleIpService workerAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerAccessibleIpListResponse>>> getWorkerAccessibleIpList(
            WorkerAccessibleIpListRequest request) {
        WorkerAccessibleIpQuery query = modelMapperHelper.map(request, WorkerAccessibleIpQuery.class);
        PageResponse<WorkerAccessibleIpResult> page = workerAccessibleIpService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(WorkerAccessibleIpListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerAccessibleIpDetailResponse>> create(
            @Valid @RequestBody WorkerAccessibleIpSaveRequest request) {
        WorkerAccessibleIpCommand command = modelMapperHelper.map(request, WorkerAccessibleIpCommand.class);

        WorkerAccessibleIpResult result = workerAccessibleIpService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerAccessibleIpDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody WorkerAccessibleIpSaveRequest request) {
        WorkerAccessibleIpCommand command = modelMapperHelper.map(request, WorkerAccessibleIpCommand.class);

        workerAccessibleIpService.update(uid, command);

        return ResponseUtil.success();
    }
}
