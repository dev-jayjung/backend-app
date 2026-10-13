package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thelounge.core.admin.worker.WorkerService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.*;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.workermanagement.dto.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/workers/{uid}")
@RequiredArgsConstructor
public class MyInfoRestController {
    private final WorkerService workerService;
    private final ModelMapperHelper mapper;

    @GetMapping
    public ResponseEntity<ApiResponse<MyInfoResponse>> get(@PathVariable Integer uid) {
        WorkerResult worker = workerService.get(uid);

        return ResponseUtil.success(mapper.map(worker, MyInfoResponse.class));
    }

    @PostMapping("/password-verifications")
    public ResponseEntity<ApiResponse<Void>> verify(@PathVariable Integer uid,
            @RequestBody MyInfoPasswordRequest request) {
        if (!workerService.verifyPassword(uid, request.getPassword())) {
            return ResponseEntity.ok(ApiResponse.fail("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다."));
        }

        return ResponseUtil.success();
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Integer uid,
            @RequestBody MyInfoUpdateRequest request) {
        workerService.update(uid, mapper.map(request, WorkerCommand.class));

        return ResponseUtil.success();
    }
}
