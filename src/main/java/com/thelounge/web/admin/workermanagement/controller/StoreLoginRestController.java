package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.store.StoreLoginService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.store.StoreLoginQuery;
import com.thelounge.core.model.store.StoreLoginResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.workermanagement.dto.StoreLoginListRequest;
import com.thelounge.web.admin.workermanagement.dto.StoreLoginListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/store-logins")
@RequiredArgsConstructor
public class StoreLoginRestController {
    private final StoreLoginService storeLoginService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StoreLoginListResponse>>> getStoreLoginList(
            StoreLoginListRequest request) {
        StoreLoginQuery query = modelMapperHelper.map(request, StoreLoginQuery.class);
        PageResponse<StoreLoginResult> page = storeLoginService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(StoreLoginListResponse.class)));
    }
}
