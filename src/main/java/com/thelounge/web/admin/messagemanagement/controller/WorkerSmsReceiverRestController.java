package com.thelounge.web.admin.messagemanagement.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.worker.WorkerSmsConcernPageService;
import com.thelounge.core.admin.worker.WorkerSmsReceiverService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerSmsReceiverCommand;
import com.thelounge.core.model.worker.WorkerSmsReceiverQuery;
import com.thelounge.core.model.worker.WorkerSmsReceiverResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.MaskUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverDetailResponse;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverListRequest;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverListResponse;
import com.thelounge.web.admin.messagemanagement.dto.WorkerSmsReceiverSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-sms-receivers")
@RequiredArgsConstructor
public class WorkerSmsReceiverRestController {

    private final WorkerSmsReceiverService workerSmsReceiverService;
    private final WorkerSmsConcernPageService workerSmsConcernPageService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<WorkerSmsReceiverListResponse>>> getReceiverList(
            WorkerSmsReceiverListRequest request) {
        WorkerSmsReceiverQuery query = modelMapperHelper.map(request, WorkerSmsReceiverQuery.class);
        PageResponse<WorkerSmsReceiverResult> page = workerSmsReceiverService.getPage(query);

        Set<String> enrolledIds = request.getConcernPageUid() != null
                ? workerSmsConcernPageService.getActiveReceiverIds(request.getConcernPageUid())
                : Set.of();

        return ResponseUtil.success(page.map(result -> toListResponse(result, enrolledIds)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkerSmsReceiverDetailResponse>> create(
            @Valid @RequestBody WorkerSmsReceiverSaveRequest request) {
        WorkerSmsReceiverCommand command = modelMapperHelper.map(request, WorkerSmsReceiverCommand.class);

        WorkerSmsReceiverResult result = workerSmsReceiverService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, WorkerSmsReceiverDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody WorkerSmsReceiverSaveRequest request) {
        WorkerSmsReceiverCommand command = modelMapperHelper.map(request, WorkerSmsReceiverCommand.class);

        workerSmsReceiverService.update(uid, command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }

    private WorkerSmsReceiverListResponse toListResponse(WorkerSmsReceiverResult result, Set<String> enrolledIds) {
        WorkerSmsReceiverListResponse response = modelMapperHelper.map(result, WorkerSmsReceiverListResponse.class);

        response.setEnrolled(enrolledIds.contains(result.getAccountId()));
        response.setAccountIdDisplay(MaskUtil.accountId(result.getAccountId()));
        response.setEmailAddressDisplay(MaskUtil.emailAddress(result.getEmailAddress()));

        return response;
    }
}
