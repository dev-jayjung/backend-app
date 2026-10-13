package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.cp.CpAccessibleIpService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.cp.CpAccessibleIpCommand;
import com.thelounge.core.model.cp.CpAccessibleIpQuery;
import com.thelounge.core.model.cp.CpAccessibleIpResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.CpAccessibleIpDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.CpAccessibleIpListRequest;
import com.thelounge.web.admin.workermanagement.dto.CpAccessibleIpListResponse;
import com.thelounge.web.admin.workermanagement.dto.CpAccessibleIpSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cp-accessible-ips")
@RequiredArgsConstructor
public class CpAccessibleIpRestController {
    private final CpAccessibleIpService cpAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CpAccessibleIpListResponse>>> getCpAccessibleIpList(
            CpAccessibleIpListRequest request) {
        CpAccessibleIpQuery query = modelMapperHelper.map(request, CpAccessibleIpQuery.class);
        PageResponse<CpAccessibleIpResult> page = cpAccessibleIpService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(CpAccessibleIpListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CpAccessibleIpDetailResponse>> create(
            @Valid @RequestBody CpAccessibleIpSaveRequest request) {
        CpAccessibleIpCommand command = modelMapperHelper.map(request, CpAccessibleIpCommand.class);

        CpAccessibleIpResult result = cpAccessibleIpService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, CpAccessibleIpDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody CpAccessibleIpSaveRequest request) {
        CpAccessibleIpCommand command = modelMapperHelper.map(request, CpAccessibleIpCommand.class);

        cpAccessibleIpService.update(uid, command);

        return ResponseUtil.success();
    }
}
