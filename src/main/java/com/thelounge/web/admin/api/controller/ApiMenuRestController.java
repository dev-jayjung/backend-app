package com.thelounge.web.admin.api.controller;

import com.thelounge.core.admin.api.ApiMenuService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiMenuCommand;
import com.thelounge.core.model.api.ApiMenuQuery;
import com.thelounge.core.model.api.ApiMenuResult;
import com.thelounge.core.model.api.ApiMenuSearchResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiMenuCreateRequest;
import com.thelounge.web.admin.api.dto.ApiMenuResponse;
import com.thelounge.web.admin.api.dto.ApiMenuSearchRequest;
import com.thelounge.web.admin.api.dto.ApiMenuSearchResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * API 메뉴 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/api-menus")
@RequiredArgsConstructor
public class ApiMenuRestController {

    private final ApiMenuService apiMenuService;
    private final ModelMapperHelper modelMapperHelper;

    /**
     * API 메뉴 목록 조회
     *
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiMenuSearchResponse>>> getList(ApiMenuSearchRequest request) {
        final ApiMenuQuery query = modelMapperHelper.map(request, ApiMenuQuery.class);
        final PageResponse<ApiMenuSearchResult> page = apiMenuService.getSearchPage(query);
        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiMenuSearchResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ApiMenuResponse>> create(@RequestBody ApiMenuCreateRequest request) {
        final ApiMenuCommand command = modelMapperHelper.map(request, ApiMenuCommand.class);
        apiMenuService.validate(command, null);
        final ApiMenuResult menu = apiMenuService.save(command);
        return ResponseUtil.success(modelMapperHelper.map(menu, ApiMenuResponse.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> update(@PathVariable Long id, @RequestBody ApiMenuCreateRequest request) {
        final ApiMenuCommand command = modelMapperHelper.map(request, ApiMenuCommand.class);
        apiMenuService.validate(command, id);
        apiMenuService.update(id, command);
        return ResponseUtil.success();
    }

}
