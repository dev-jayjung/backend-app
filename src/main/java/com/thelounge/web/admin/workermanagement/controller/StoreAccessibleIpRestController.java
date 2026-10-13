package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.store.StoreAccessibleIpService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.store.StoreAccessibleIpCommand;
import com.thelounge.core.model.store.StoreAccessibleIpQuery;
import com.thelounge.core.model.store.StoreAccessibleIpResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.StoreAccessibleIpDetailResponse;
import com.thelounge.web.admin.workermanagement.dto.StoreAccessibleIpListRequest;
import com.thelounge.web.admin.workermanagement.dto.StoreAccessibleIpListResponse;
import com.thelounge.web.admin.workermanagement.dto.StoreAccessibleIpSaveRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/store-accessible-ips")
@RequiredArgsConstructor
public class StoreAccessibleIpRestController {
    private final StoreAccessibleIpService storeAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StoreAccessibleIpListResponse>>> getStoreAccessibleIpList(
            StoreAccessibleIpListRequest request) {
        StoreAccessibleIpQuery query = modelMapperHelper.map(request, StoreAccessibleIpQuery.class);
        PageResponse<StoreAccessibleIpResult> page = storeAccessibleIpService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(StoreAccessibleIpListResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StoreAccessibleIpDetailResponse>> create(
            @Valid @RequestBody StoreAccessibleIpSaveRequest request) {
        StoreAccessibleIpCommand command = modelMapperHelper.map(request, StoreAccessibleIpCommand.class);

        StoreAccessibleIpResult result = storeAccessibleIpService.save(command);

        return ResponseUtil.success(modelMapperHelper.map(result, StoreAccessibleIpDetailResponse.class));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long uid,
            @Valid @RequestBody StoreAccessibleIpSaveRequest request) {
        StoreAccessibleIpCommand command = modelMapperHelper.map(request, StoreAccessibleIpCommand.class);

        storeAccessibleIpService.update(uid, command);

        return ResponseUtil.success();
    }
}
