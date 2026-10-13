package com.thelounge.web.admin.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.admin.AdminMenuBookmarkService;
import com.thelounge.core.admin.admin.AdminMenuService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.admin.AdminMenuQuery;
import com.thelounge.core.model.admin.AdminMenuResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.admin.dto.AdminMenuResponse;
import com.thelounge.web.admin.common.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin-menus")
@RequiredArgsConstructor
public class AdminMenuRestController {

    private final AdminMenuService adminMenuService;
    private final AdminMenuBookmarkService adminMenuBookmarkService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping("/by-url")
    public ResponseEntity<ApiResponse<AdminMenuResponse>> getAdminMenuByUrl(@RequestParam String url) {
        AdminMenuResult result = adminMenuService.getMenuByLinkUrl(url);

        return ResponseUtil.success(modelMapperHelper.map(result, AdminMenuResponse.class));
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<ApiResponse<List<AdminMenuResponse>>> getBookmarks() {
        AdminMenuQuery query = AdminMenuQuery.builder()
                .workerId(SecurityUtil.getWorkerId())
                .build();
        List<AdminMenuResult> bookmarkList = adminMenuService.getBookmarkList(query);

        return ResponseUtil.success(modelMapperHelper.mapList(bookmarkList, AdminMenuResponse.class));
    }

    @PostMapping("/{menuCode}/bookmark")
    public ResponseEntity<ApiResponse<Boolean>> addBookmark(@PathVariable String menuCode) {
        boolean isBookmark = adminMenuBookmarkService.toggleBookmark(SecurityUtil.getWorkerId(), menuCode);

        return ResponseUtil.success(isBookmark);
    }
}