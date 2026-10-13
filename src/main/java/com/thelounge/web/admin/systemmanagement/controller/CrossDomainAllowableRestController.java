package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.CrossDomainAllowableService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.CrossDomainAllowableCommand;
import com.thelounge.core.model.system.CrossDomainAllowableQuery;
import com.thelounge.core.model.system.CrossDomainAllowableResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.CrossDomainAllowableDetailResponse;
import com.thelounge.web.admin.systemmanagement.dto.CrossDomainAllowableListRequest;
import com.thelounge.web.admin.systemmanagement.dto.CrossDomainAllowableListResponse;
import com.thelounge.web.admin.systemmanagement.dto.CrossDomainAllowableSaveRequest;
import com.thelounge.web.admin.common.util.SecurityUtil;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cross-domain-allowables")
@RequiredArgsConstructor
public class CrossDomainAllowableRestController {

    private final CrossDomainAllowableService crossDomainAllowableService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CrossDomainAllowableListResponse>>> getCrossDomainAllowableList(
            CrossDomainAllowableListRequest request) {
        CrossDomainAllowableQuery query = modelMapperHelper.map(request, CrossDomainAllowableQuery.class);
        PageResponse<CrossDomainAllowableResult> page = crossDomainAllowableService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(CrossDomainAllowableListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CrossDomainAllowableDetailResponse>> create(
            @Valid @RequestBody CrossDomainAllowableSaveRequest request) {
        CrossDomainAllowableCommand command = modelMapperHelper.map(request, CrossDomainAllowableCommand.class);

        CrossDomainAllowableResult result = crossDomainAllowableService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, CrossDomainAllowableDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody CrossDomainAllowableSaveRequest request) {
        CrossDomainAllowableCommand command = modelMapperHelper.map(request, CrossDomainAllowableCommand.class);

        crossDomainAllowableService.update(uid, command, SecurityUtil.getWorkerIdOrNull());

        return ResponseUtil.success();
    }
}
