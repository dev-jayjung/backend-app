package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thelounge.core.admin.admin.AdminMenuSearchHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.admin.AdminMenuSearchHistoryQuery;
import com.thelounge.core.model.admin.AdminMenuSearchHistoryResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/v1/admin-menu-search-histories")
public class PersonalInfoSearchHistoryRestController {
    private final AdminMenuSearchHistoryService service;
    public PersonalInfoSearchHistoryRestController(AdminMenuSearchHistoryService service) { this.service = service; }
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AdminMenuSearchHistoryResult>>> list(AdminMenuSearchHistoryQuery query) {
        return ResponseUtil.success(service.getPage(query));
    }
}
