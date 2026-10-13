package com.thelounge.web.admin.workermanagement.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.admin.AdminMenuAccessHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.admin.AdminMenuAccessHistoryQuery;
import com.thelounge.core.model.admin.AdminMenuAccessHistoryResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin-menu-access-histories")
public class MenuAccessHistoryRestController {
    /** 헤더 클릭 정렬을 허용하는 컬럼. 공통 PageRequest.getOrderBy() 는 컬럼 검증을 하지 않으므로 여기서 검증한다. */
    private static final Set<String> SORTABLE_COLUMNS = Set.of("uid", "regDate");

    private static final String DEFAULT_SORT_BY = "uid";

    private static final String DEFAULT_DIRECTION = "DESC";

    private final AdminMenuAccessHistoryService service;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AdminMenuAccessHistoryResult>>> list(
            AdminMenuAccessHistoryQuery query) {
        applySortWhitelist(query);

        return ResponseUtil.success(service.getPage(query));
    }

    /** 화이트리스트에 없는 정렬 요청은 기본 정렬(uid DESC)로 되돌린다. */
    private void applySortWhitelist(AdminMenuAccessHistoryQuery query) {
        if (!SORTABLE_COLUMNS.contains(query.getSortBy())) {
            query.setSortBy(DEFAULT_SORT_BY);
            query.setDirection(DEFAULT_DIRECTION);
        }
    }
}
