package com.thelounge.web.admin.systemmanagement.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.ErrorCodeService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.ErrorCodeCommand;
import com.thelounge.core.model.system.ErrorCodeQuery;
import com.thelounge.core.model.system.ErrorCodeResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;
import com.thelounge.web.admin.systemmanagement.dto.ErrorCodeDetailResponse;
import com.thelounge.web.admin.systemmanagement.dto.ErrorCodeListRequest;
import com.thelounge.web.admin.systemmanagement.dto.ErrorCodeListResponse;
import com.thelounge.web.admin.systemmanagement.dto.ErrorCodeSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/error-codes")
@RequiredArgsConstructor
public class ErrorCodeRestController {

    private final ErrorCodeService errorCodeService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ErrorCodeListResponse>>> getErrorCodeList(
            ErrorCodeListRequest request) {
        ErrorCodeQuery query = modelMapperHelper.map(request, ErrorCodeQuery.class);
        PageResponse<ErrorCodeResult> page = errorCodeService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(ErrorCodeListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ErrorCodeDetailResponse>> create(
            @Valid @RequestBody ErrorCodeSaveRequest request) {
        ErrorCodeCommand command = modelMapperHelper.map(request, ErrorCodeCommand.class);

        ErrorCodeResult result = errorCodeService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, ErrorCodeDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody ErrorCodeSaveRequest request) {
        ErrorCodeCommand command = modelMapperHelper.map(request, ErrorCodeCommand.class);

        errorCodeService.update(uid, command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }
}
