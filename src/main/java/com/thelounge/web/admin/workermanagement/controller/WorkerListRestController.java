package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerCommand;
import com.thelounge.core.model.worker.WorkerQuery;
import com.thelounge.core.model.worker.WorkerResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.WorkerDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerListRequest;
import com.thelounge.web.admin.workermanagement.dto.WorkerListResponse;
import com.thelounge.web.admin.workermanagement.dto.WorkerSaveRequest;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/workers")
@RequiredArgsConstructor
public class WorkerListRestController {

    private final WorkerService workerService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerListResponse>>> getWorkerList(
            WorkerListRequest request) {
        WorkerQuery query = modelMapperHelper.map(request, WorkerQuery.class);
        PageResponse<WorkerResult> page = workerService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(WorkerListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerDetailResponse>> create(
            @Validated({ Default.class, WorkerSaveRequest.Create.class }) @RequestBody WorkerSaveRequest request) {
        WorkerCommand command = modelMapperHelper.map(request, WorkerCommand.class);

        WorkerResult result = workerService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerDetailResponse.class));
    }

    /**
     * 작업자 계정 수정.
     *
     * <p>PUT /api/v1/workers/{uid} 는 내 정보 화면(MyInfoRestController)이 쓰고 있어
     * 하위 경로로 분리했다.
     */
    @PutMapping("/{uid}/account")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Integer uid,
            @Valid @RequestBody WorkerSaveRequest request) {
        WorkerCommand command = modelMapperHelper.map(request, WorkerCommand.class);

        workerService.update(uid, command);

        return ResponseUtil.success();
    }
}
