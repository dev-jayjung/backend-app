package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerLoginService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerLoginQuery;
import com.thelounge.core.model.worker.WorkerLoginResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.WorkerLoginListRequest;
import com.thelounge.web.admin.workermanagement.dto.WorkerLoginListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/work-logins")
@RequiredArgsConstructor
public class WorkerLoginRestController {
    private final WorkerLoginService workerLoginService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerLoginListResponse>>> getWorkerLoginList(
            WorkerLoginListRequest request) {
        WorkerLoginQuery query = modelMapperHelper.map(request, WorkerLoginQuery.class);
        PageResponse<WorkerLoginResult> page = workerLoginService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(WorkerLoginListResponse.class)));
    }
}
