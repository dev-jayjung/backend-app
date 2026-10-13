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

import com.thelounge.core.admin.cp.CpService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.web.admin.workermanagement.type.CpServiceGroup;
import com.thelounge.web.admin.workermanagement.type.WorkerServiceStatus;
import com.thelounge.core.model.cp.CpCommand;
import com.thelounge.core.model.cp.CpQuery;
import com.thelounge.core.model.cp.CpResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.CpDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.CpListRequest;
import com.thelounge.web.admin.workermanagement.dto.CpListResponse;
import com.thelounge.web.admin.workermanagement.dto.CpSaveRequest;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cps")
@RequiredArgsConstructor
public class CpListRestController {
    private final CpService cpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CpListResponse>>> getCpList(CpListRequest request) {
        CpQuery query = modelMapperHelper.map(request, CpQuery.class);
        PageResponse<CpResult> page = cpService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CpDetailResponse>> create(
            @Validated({ Default.class, CpSaveRequest.Create.class }) @RequestBody CpSaveRequest request) {
        CpCommand command = modelMapperHelper.map(request, CpCommand.class);

        CpResult result = cpService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, CpDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody CpSaveRequest request) {
        CpCommand command = modelMapperHelper.map(request, CpCommand.class);

        cpService.update(uid, command);

        return ResponseUtil.success();
    }

    private CpListResponse toListResponse(CpResult result) {
        CpListResponse response = modelMapperHelper.map(result, CpListResponse.class);

        response.setCpGroupName(CpServiceGroup.descriptionOf(result.getCpGroup()));
        response.setServiceStatusName(WorkerServiceStatus.descriptionOf(result.getServiceStatus()));

        return response;
    }
}
