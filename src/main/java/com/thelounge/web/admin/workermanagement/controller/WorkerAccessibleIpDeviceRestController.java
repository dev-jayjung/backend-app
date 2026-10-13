package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerAccessibleIpDeviceService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerAccessibleIpDeviceCommand;
import com.thelounge.core.model.worker.WorkerAccessibleIpDeviceQuery;
import com.thelounge.core.model.worker.WorkerAccessibleIpDeviceResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpDeviceDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpDeviceListRequest;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpDeviceListResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleIpDeviceSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-accessible-ip-devices")
@RequiredArgsConstructor
public class WorkerAccessibleIpDeviceRestController {
    private final WorkerAccessibleIpDeviceService workerAccessibleIpDeviceService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerAccessibleIpDeviceListResponse>>> getWorkerAccessibleIpDeviceList(
            WorkerAccessibleIpDeviceListRequest request) {
        WorkerAccessibleIpDeviceQuery query = modelMapperHelper.map(request, WorkerAccessibleIpDeviceQuery.class);
        PageResponse<WorkerAccessibleIpDeviceResult> page = workerAccessibleIpDeviceService.getPage(query);

        return ResponseUtil
                .success(page.map(modelMapperHelper.map(WorkerAccessibleIpDeviceListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerAccessibleIpDeviceDetailResponse>> create(
            @Valid @RequestBody WorkerAccessibleIpDeviceSaveRequest request) {
        WorkerAccessibleIpDeviceCommand command = modelMapperHelper.map(request,
                WorkerAccessibleIpDeviceCommand.class);

        WorkerAccessibleIpDeviceResult result = workerAccessibleIpDeviceService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerAccessibleIpDeviceDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody WorkerAccessibleIpDeviceSaveRequest request) {
        WorkerAccessibleIpDeviceCommand command = modelMapperHelper.map(request,
                WorkerAccessibleIpDeviceCommand.class);

        workerAccessibleIpDeviceService.update(uid, command);

        return ResponseUtil.success();
    }
}
