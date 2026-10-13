package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thelounge.core.admin.admin.AdminMenuPermissionHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.admin.AdminMenuPermissionHistoryQuery;
import com.thelounge.core.model.admin.AdminMenuPermissionHistoryResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin-menu-permission-histories")
@RequiredArgsConstructor
public class MenuPermissionHistoryRestController {
    private final AdminMenuPermissionHistoryService service;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AdminMenuPermissionHistoryResult>>> list(
            AdminMenuPermissionHistoryQuery query) {
        return ResponseUtil.success(service.getPage(query));
    }
}
