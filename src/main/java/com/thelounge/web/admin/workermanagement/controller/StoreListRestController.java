package com.thelounge.web.admin.workermanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.store.StoreService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.store.StoreQuery;
import com.thelounge.core.model.store.StoreResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.StoreListRequest;
import com.thelounge.web.admin.workermanagement.dto.StoreListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreListRestController {
    private final StoreService storeService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StoreListResponse>>> getStoreList(StoreListRequest request) {
        StoreQuery query = modelMapperHelper.map(request, StoreQuery.class);
        PageResponse<StoreResult> page = storeService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(StoreListResponse.class)));
    }

    /** 그룹 회원 유형 필터 옵션. 코드-라벨 매핑표가 없어 실제 데이터의 DISTINCT 값을 내려준다. */
    @GetMapping("/store-groups")
    public ResponseEntity<ApiResponse<List<String>>> getStoreGroups() {
        return ResponseUtil.success(storeService.getStoreGroups());
    }
}
