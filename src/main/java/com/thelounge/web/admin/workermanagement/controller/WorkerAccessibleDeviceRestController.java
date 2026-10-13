package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerAccessibleDeviceService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerAccessibleDeviceCommand;
import com.thelounge.core.model.worker.WorkerAccessibleDeviceQuery;
import com.thelounge.core.model.worker.WorkerAccessibleDeviceResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleDeviceDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleDeviceListRequest;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleDeviceListResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleDeviceSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-accessible-devices")
@RequiredArgsConstructor
public class WorkerAccessibleDeviceRestController {
    private final WorkerAccessibleDeviceService workerAccessibleDeviceService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerAccessibleDeviceListResponse>>> getWorkerAccessibleDeviceList(
            WorkerAccessibleDeviceListRequest request) {
        WorkerAccessibleDeviceQuery query = modelMapperHelper.map(request, WorkerAccessibleDeviceQuery.class);
        PageResponse<WorkerAccessibleDeviceResult> page = workerAccessibleDeviceService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(WorkerAccessibleDeviceListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerAccessibleDeviceDetailResponse>> create(
            @Valid @RequestBody WorkerAccessibleDeviceSaveRequest request) {
        WorkerAccessibleDeviceCommand command = modelMapperHelper.map(request,
                WorkerAccessibleDeviceCommand.class);

        WorkerAccessibleDeviceResult result = workerAccessibleDeviceService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerAccessibleDeviceDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody WorkerAccessibleDeviceSaveRequest request) {
        WorkerAccessibleDeviceCommand command = modelMapperHelper.map(request,
                WorkerAccessibleDeviceCommand.class);

        workerAccessibleDeviceService.update(uid, command);

        return ResponseUtil.success();
    }
}
