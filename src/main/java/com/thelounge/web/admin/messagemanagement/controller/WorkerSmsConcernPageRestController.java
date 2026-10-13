package com.thelounge.web.admin.messagemanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerSmsConcernPageService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerSmsConcernPageCommand;
import com.thelounge.core.model.worker.WorkerSmsConcernPageQuery;
import com.thelounge.core.model.worker.WorkerSmsConcernPageResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsConcernPageDetailResponse;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsConcernPageListRequest;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsConcernPageListResponse;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsConcernPageSaveRequest;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverMappingRequest;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-sms-concern-pages")
@RequiredArgsConstructor
public class WorkerSmsConcernPageRestController {

    private final WorkerSmsConcernPageService workerSmsConcernPageService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerSmsConcernPageListResponse>>> getConcernPageList(
            WorkerSmsConcernPageListRequest request) {
        WorkerSmsConcernPageQuery query = modelMapperHelper.map(request, WorkerSmsConcernPageQuery.class);
        PageResponse<WorkerSmsConcernPageResult> page = workerSmsConcernPageService.getPageWithReceivers(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(WorkerSmsConcernPageListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerSmsConcernPageDetailResponse>> create(
            @Validated({ Default.class, WorkerSmsConcernPageSaveRequest.Create.class })
            @RequestBody WorkerSmsConcernPageSaveRequest request) {
        WorkerSmsConcernPageCommand command = modelMapperHelper.map(request, WorkerSmsConcernPageCommand.class);

        WorkerSmsConcernPageResult result = workerSmsConcernPageService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerSmsConcernPageDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody WorkerSmsConcernPageSaveRequest request) {
        WorkerSmsConcernPageCommand command = modelMapperHelper.map(request, WorkerSmsConcernPageCommand.class);

        workerSmsConcernPageService.update(uid, command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }

    @PostMapping("/{uid}/receivers")
    public ResponseEntity<ApiResponse<Void>> enrollReceiver(@PathVariable Long uid,
            @Valid @RequestBody WorkerSmsReceiverMappingRequest request) {
        workerSmsConcernPageService.enrollReceiver(uid, request.getReceiverId());

        return ResponseUtil.success();
    }

    @DeleteMapping("/{uid}/receivers")
    public ResponseEntity<ApiResponse<Void>> excludeReceiver(@PathVariable Long uid,
            @Valid @RequestBody WorkerSmsReceiverMappingRequest request) {
        workerSmsConcernPageService.excludeReceiver(uid, request.getReceiverId());

        return ResponseUtil.success();
    }
}
